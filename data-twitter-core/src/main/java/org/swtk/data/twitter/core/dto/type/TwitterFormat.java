package org.swtk.data.twitter.core.dto.type;

public enum TwitterFormat {

	/**
	 * 	TDC = Twitter.com
	 * 	the official Twitter format
	 */
	TDC,

	/**
	 * 	the GNIP format for tweets
	 * 	GNIP no longer streams twitter; but the format is still prevelant
	 */
	GNIP,

	/**
	 * 	Cannonical form is specific to SWTK-data
	 */
	CANNONICAL;

	public static TwitterFormat find(String text) {
		for (TwitterFormat value : TwitterFormat.values())
			if (value.toString().equalsIgnoreCase(text)) return value;
		return null;
	}

	public static String list() {
		StringBuilder sb = new StringBuilder();

		TwitterFormat[] values = TwitterFormat.values();
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i]);
			if (i + 1 < values.length) sb.append(", ");
		}

		return sb.toString().trim();
	}
}
