package org.swtk.data.twitter.core.dto.generic.adapter;

import com.trimc.blogger.commons.LogManager;

public final class TweetAdapter {

	public static LogManager logger = new LogManager(TweetAdapter.class);

	//	public static Tweet transform(String text) throws AdapterValidationException {
	//		try {
	//
	//			/*	GNIP format	*/
	//			if (text.startsWith("{\"id\":\"")) {
	//				return transform(TweetGnipAdapter.transform(text));
	//			}
	//
	//			return GsonUtils.toObject(text, Tweet.class);
	//
	//		} catch (Exception e) {
	//			logger.error(e);
	//			throw new AdapterValidationException("Unable to deserialize tweet (expected-type = 'generic', length = %s)", text.length());
	//		}
	//	}

	//	public static Tweet transform(TweetGnip v1) throws AdapterValidationException {
	//		Tweet tweet = new Tweet();
	//
	//		tweet.setText(StringUtils.trim(v1.getBody()));
	//		tweet.setUser(v1.getActor().getPreferredUsername());
	//
	//		return tweet;
	//	}
	//
	//	public static Tweet transform(TweetV2 v2) throws AdapterValidationException {
	//		Tweet tweet = new Tweet();
	//
	//		tweet.setText(v2.getText());
	//		tweet.setUser(v2.getUser().getName());
	//
	//		return tweet;
	//	}
}
