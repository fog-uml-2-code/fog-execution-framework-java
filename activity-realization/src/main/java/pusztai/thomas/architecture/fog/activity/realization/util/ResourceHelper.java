package pusztai.thomas.architecture.fog.activity.realization.util;

import pusztai.thomas.architecture.fog.activity.realization.dto.ActivityInfoDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceHelper {

	public static String readResourceAsString(String resourceName) {
		InputStream input = null;
		BufferedReader r = null;
		StringBuilder sb = new StringBuilder();
		try {
			input = ActivityInfoDto.class.getResourceAsStream(resourceName);
			r = new BufferedReader(new InputStreamReader(input));
			r.lines().forEach(line -> sb.append(line));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			try {
				r.close();
				input.close();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
		return sb.toString();
	}

}
