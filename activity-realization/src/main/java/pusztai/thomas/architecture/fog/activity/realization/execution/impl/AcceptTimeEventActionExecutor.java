package pusztai.thomas.architecture.fog.activity.realization.execution.impl;

import io.micronaut.inject.qualifiers.Qualifiers;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.TaskScheduler;
import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityNodeInfoDto;
import pusztai.thomas.architecture.fog.activity.realization.execution.*;

import java.time.Duration;

public class AcceptTimeEventActionExecutor extends SingleNextNodeExecutorBase {

	public static class Factory implements ActivityNodeExecutorFactory {
		@Override
		public ActivityNodeExecutor createNodeExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
			return new AcceptTimeEventActionExecutor(nodeInfo, executionManager);
		}
	}

	private final TaskScheduler taskScheduler;
	private final Duration duration;

	public AcceptTimeEventActionExecutor(ActivityNodeInfoDto nodeInfo, ActivityExecutionManager executionManager) {
		super(nodeInfo, executionManager);
		this.taskScheduler = executionManager.getBeanContext().getBean(TaskScheduler.class, Qualifiers.byName(TaskExecutors.SCHEDULED));
		this.duration = Duration.ofMillis(nodeInfo.durationMsec);
	}

	@Override
	public void execute(ActivityNodeExecutor prevNode, Object param) throws ActivityExecutionException {
		this.taskScheduler.schedule(this.duration, () -> this.executeNextNode(null));
	}

	@Override
	public int getMaxRetryCount() {
		return 0;
	}
}
