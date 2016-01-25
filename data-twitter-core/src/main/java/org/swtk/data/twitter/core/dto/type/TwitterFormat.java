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
	CANNONICAL
}
