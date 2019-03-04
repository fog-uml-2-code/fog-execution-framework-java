package pusztai.thomas.architecture.fog.activity.realization.dto;

public class ActivityNodeInfoDto {

	public String id;
	public ActivityNodeType type;
	public String name;
	public String nextNode;
	public String target;
	public String operation;
	public String handler;
	public String[] prevNodes;
	public String[] nextNodes;
	public ConditionInfoDto[] conditions;
	public String elseNextNode;
	public Long durationMsec;

}
