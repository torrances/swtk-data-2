package org.swtk.data.twitter.tensorflow.util;

import java.util.HashMap;
import java.util.Map;

import org.swtk.data.twitter.core.dto.type.AgeRange;
import org.swtk.data.twitter.core.dto.type.Gender;

import com.trimc.blogger.commons.utils.string.StringUtils;

public class LookupTable {

	public static Map<Integer, String> map = new HashMap<Integer, String>();

	static {
		add(Gender.MALE, AgeRange.R_12_17, 1);
		add(Gender.MALE, AgeRange.R_18_24, 2);
		add(Gender.MALE, AgeRange.R_25_34, 3);
		add(Gender.MALE, AgeRange.R_35_44, 4);
		add(Gender.MALE, AgeRange.R_45_54, 5);
		add(Gender.MALE, AgeRange.R_55_64, 6);
		add(Gender.MALE, AgeRange.R_65_plus, 7);
		add(Gender.MALE, AgeRange.R_unknown, 8);
		add(Gender.FEMALE, AgeRange.R_12_17, 9);
		add(Gender.FEMALE, AgeRange.R_18_24, 10);
		add(Gender.FEMALE, AgeRange.R_25_34, 11);
		add(Gender.FEMALE, AgeRange.R_35_44, 12);
		add(Gender.FEMALE, AgeRange.R_45_54, 13);
		add(Gender.FEMALE, AgeRange.R_55_64, 14);
		add(Gender.FEMALE, AgeRange.R_65_plus, 15);
		add(Gender.FEMALE, AgeRange.R_unknown, 16);
	}

	private static void add(Gender gender, AgeRange ageRange, Integer clazz) {
		map.put(clazz, key(gender, ageRange));
	}

	public static String generate() {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			sb.append(entry.getKey());
			sb.append("\t");
			sb.append(StringUtils.substringBefore(entry.getValue(), "-").trim());
			sb.append("\t");
			sb.append(StringUtils.substringAfter(entry.getValue(), "-").trim());
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	private static String key(Gender gender, AgeRange ageRange) {
		StringBuilder sb = new StringBuilder();

		sb.append(gender.toString().toLowerCase());
		sb.append("-");
		sb.append(ageRange.toString().toLowerCase());

		return sb.toString();
	}

	public static void main(String... args) throws Throwable {
		System.err.println(generate());
	}
}
