package pusztai.thomas.architecture.fog.activity.realization.dto;

import pusztai.thomas.architecture.fog.activity.realization.util.JsonHelper;
import pusztai.thomas.architecture.fog.activity.realization.util.ResourceHelper;

public class ActivityInfoDto {

	public String name;
	public String initialNode;
	public ActivityNodeInfoDto[] nodes;

	public static ActivityInfoDto loadFromResource(String resourceName) {
		String json = ResourceHelper.readResourceAsString(resourceName);
		return JsonHelper.parseJson(json, ActivityInfoDto.class);
	}

}
