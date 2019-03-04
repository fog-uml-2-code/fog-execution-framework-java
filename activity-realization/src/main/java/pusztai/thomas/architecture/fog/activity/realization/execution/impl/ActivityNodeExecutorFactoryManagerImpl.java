package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionManager;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutor;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutorFactory;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutorFactoryManager;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeType;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class ActivityNodeExecutorFactoryManagerImpl implements ActivityNodeExecutorFactoryManager {

	private final Map<ActivityNodeType, ActivityNodeExecutorFactory> factories = new HashMap<>();

	public ActivityNodeExecutorFactoryManagerImpl() {
		factories.put(ActivityNodeType.AcceptEventAction, new AcceptEventActionExecutor.Factory());
		factories.put(ActivityNodeType.AcceptTimeEventAction, new AcceptTimeEventActionExecutor.Factory());
		factories.put(ActivityNodeType.InitialNode, new InitialNodeExecutor.Factory());
		factories.put(ActivityNodeType.CallOperationAction, new CallOperationActionExecutor.Factory());
		factories.put(ActivityNodeType.DecisionNode, new DecisionNodeExecutor.Factory());
		factories.put(ActivityNodeType.ForkNode, new ForkNodeExecutor.Factory());
		factories.put(ActivityNodeType.JoinNode, new JoinNodeExecutor.Factory());
		factories.put(ActivityNodeType.MergeNode, new MergeNodeExecutor.Factory());
		factories.put(ActivityNodeType.OpaqueAction, new OpaqueActionExecutor.Factory());
	}

	@Override
	public ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		ActivityNodeExecutorFactory factory = factories.get(nodeInfo.type);
		if (factory != null) {
			return factory.createNodeExecutor(nodeInfo, executionManager);
		} else {
			throw new RuntimeException("No ActivityNodeExecutor found for node type: " + nodeInfo.type.toString());
		}
	}

}
