package org.swtk.data.twitter.core.dto.gnip.adapter;

import org.swtk.data.twitter.core.dto.gnip.GnipTweet;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.GsonUtils;

public final class GnipTweetAdapter {

	public static LogManager logger = new LogManager(GnipTweetAdapter.class);

	public static GnipTweet transform(String text) throws AdapterValidationException {
		try {
			return GsonUtils.toObject(text, GnipTweet.class);

		} catch (Exception e) {
			logger.error(e);
			throw new AdapterValidationException("Unable to deserialize tweet (expected-type = 'gnip', length = %s)", text.length());
		}
	}
}
