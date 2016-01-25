package org.swtk.data.twitter.core.dto.tdc.adapter;

import org.swtk.data.twitter.core.dto.tdc.TdcTweet;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.GsonUtils;

public final class TdcTweetAdapter {

	public static LogManager logger = new LogManager(TdcTweetAdapter.class);

	public static TdcTweet transform(String text) throws AdapterValidationException {
		try {
			return GsonUtils.toObject(text, TdcTweet.class);

		} catch (Exception e) {
			logger.error(e);
			throw new AdapterValidationException("Unable to deserialize tweet (expected-type = 'v2', length = %s)", text.length());
		}
	}
}
