package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.TaskScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionException;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionManager;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutor;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutorFactoryManager;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Prototype
public class ActivityExecutionManagerImpl implements ActivityExecutionManager {

	private static final Logger LOG = LoggerFactory.getLogger(ActivityExecutionManagerImpl.class);

	private final ActivityNodeExecutorFactoryManager nodeExecutorFactoryManager;
	private final Map<String, ActivityNodeExecutor> nodesDirectory = new HashMap<>();
	private final BeanContext beanContext;
	private final TaskScheduler taskScheduler;
	private String activityName;
	private String initialNodeId;

	@Inject
	public ActivityExecutionManagerImpl(BeanContext beanContext, ActivityNodeExecutorFactoryManager nodeExecutorFactoryManager,
	                                    @Named(TaskExecutors.SCHEDULED) TaskScheduler taskScheduler) {
		this.beanContext = beanContext;
		this.nodeExecutorFactoryManager = nodeExecutorFactoryManager;
		this.taskScheduler = taskScheduler;
	}

	@Override
	public BeanContext getBeanContext() {
		return beanContext;
	}

	@Override
	public ActivityNodeExecutor getActivityNode(String id) {
		return this.nodesDirectory.get(id);
	}

	@Override
	public void loadActivity(ActivityInfoDto activityInfo) {
		if (!nodesDirectory.isEmpty()) {
			throw new RuntimeException("An activity has already been loaded.");
		}

		LOG.info("Loading activity: {}.", activityInfo.name);
		this.activityName = activityInfo.name;
		this.initialNodeId = activityInfo.initialNode;

		for (ActivityNodeInfoDto nodeInfo : activityInfo.nodes) {
			ActivityNodeExecutor nodeExecutor = nodeExecutorFactoryManager.createNodeExecutor(nodeInfo, this);
			nodesDirectory.put(nodeExecutor.getId(), nodeExecutor);
			LOG.info("Successfully created executor for: {}.", nodeExecutor.toString());
		}

		LOG.info("Activity {} successfully loaded.", activityName);
	}

	@Override
	public void startActivity() {
		executeNode(null, initialNodeId, null);
	}

	@Override
	public void executeNode(ActivityNodeExecutor currNode, String nextNodeId, Object param) {
		ActivityNodeExecutor nextNode = nodesDirectory.get(nextNodeId);
		if (nextNode == null) {
			throw new RuntimeException("Invalid nextNodeId: ".concat(nextNodeId));
		}
		LOG.info("Executing node {}.", nextNode.getName());
		taskScheduler.schedule(Duration.ZERO, () -> executeNodeInternal(nextNode, currNode, param));
	}

	/**
	 * Executes the specified node (this method is called on the thread that should execute the node).
	 */
	private void executeNodeInternal(ActivityNodeExecutor node, ActivityNodeExecutor prevNode, Object param) {
		int maxRetries = Math.min(node.getMaxRetryCount(), this.MAX_NODE_RETRIES);
		int retries = 0;
		do {
			try {
				node.execute(prevNode, param);
				return;
			} catch (ActivityExecutionException ex) {
				LOG.info("Exception while executing node {}. Details: {}", node, ex);
				++retries;
			}
		} while (retries <= maxRetries);

		String goToNodeOnError = node.getNextNodeIdIfError();
		if (goToNodeOnError != null && !goToNodeOnError.equals(node.getId())) {
			this.executeNode(node, goToNodeOnError, param);
		} else {
			this.executeNode(node, initialNodeId, null);
		}
	}

}
