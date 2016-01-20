package org.swtk.data.twitter.core.dto.v2.adapter;

import org.swtk.data.twitter.core.dto.v2.TweetV2;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.GsonUtils;

public final class TweetV2Adapter {

	public static LogManager logger = new LogManager(TweetV2Adapter.class);

	public static TweetV2 transform(String text) throws AdapterValidationException {
		try {
			return GsonUtils.toObject(text, TweetV2.class);

		} catch (Exception e) {
			logger.error(e);
			throw new AdapterValidationException("Unable to deserialize tweet (expected-type = 'v2', length = %s)", text.length());
		}
	}
}
