package org.swtk.data.twitter.core.dto.type;

public enum AgeRange {

	R_12_17,

	R_18_24,

	R_25_34,

	R_35_44,

	R_45_54,

	R_55_64,

	R_65_plus,

	R_unknown;

	public static AgeRange find(Integer age) {
		if (age >= 12 && age <= 17) return R_12_17;
		if (age >= 18 && age <= 24) return R_18_24;
		if (age >= 25 && age <= 34) return R_25_34;
		if (age >= 35 && age <= 44) return R_35_44;
		if (age >= 45 && age <= 54) return R_45_54;
		if (age >= 55 && age <= 64) return R_55_64;
		if (age >= 65) return R_65_plus;
		
		return R_unknown;
	}
}
