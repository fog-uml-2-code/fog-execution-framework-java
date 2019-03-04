package pusztai.thomas.architecture.fog.activity.realization.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonHelper {

	private static ObjectMapper objMapper = new ObjectMapper();

	public static <T> T parseJson(String json, Class<T> type) {
		try {
			return objMapper.readValue(json, type);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String stringify(Object obj) {
		try {
			return objMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
