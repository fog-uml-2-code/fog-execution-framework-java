package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionException;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityExecutionManager;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutor;
import pusztai.thomas.architecture.fog.activity.realization.execution.ActivityNodeExecutorFactory;

import java.util.HashSet;
import java.util.Set;

public class JoinNodeExecutor extends SingleNextNodeExecutorBase {

	public static class Factory implements ActivityNodeExecutorFactory {
		@Override
		public ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
			return new JoinNodeExecutor(nodeInfo, executionManager);
		}
	}

	private final Set<String> prevNodes = new HashSet<>();
	private final Set<String> tokensArrivedFrom = new HashSet<>();

	public JoinNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
		for (String node : nodeInfo.prevNodes) {
			this.prevNodes.add(node);
		}
	}

	@Override
	public void execute(ActivityNodeExecutor prevNode, Object param) throws ActivityExecutionException {
		String prevNodeId = prevNode.getId();

		if (this.prevNodes.contains(prevNodeId)) {
			this.tokensArrivedFrom.add(prevNodeId);

			if (this.tokensArrivedFrom.size() == this.prevNodes.size()) {
				this.tokensArrivedFrom.clear();
				this.executeNextNode(null);
			}
		}
	}

	@Override
	public int getMaxRetryCount() {
		return 0;
	}

}
