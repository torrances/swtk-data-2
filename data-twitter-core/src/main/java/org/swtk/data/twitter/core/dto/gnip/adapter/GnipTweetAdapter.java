package org.swtk.data.twitter.core.dto.gnip.adapter;

import org.swtk.data.twitter.core.dto.gnip.GnipTweet;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.GsonUtils;

public final class GnipTweetAdapter {

	public static LogManager logger = new LogManager(GnipTweetAdapter.class);

	public static String toString(GnipTweet gnipTweet) throws AdapterValidationException {
		try {
			return GsonUtils.toJsonSingleLineAll(gnipTweet);

		} catch (Exception e) {
			logger.error(e);
			throw new AdapterValidationException("Unable to serialize tweet (expected-type = 'gnip', id = %s)", gnipTweet.getId());
		}
	}

	public static GnipTweet transform(String text) throws AdapterValidationException {
		try {
			return GsonUtils.toObject(text, GnipTweet.class);

		} catch (Exception e1) {

			/**
			 * 	Purpose:
			 * 	it's possible the tweet is missing a termininating "}"
			 */
			try {
				return transform(text.concat("}"));
			} catch (Exception e2) {
				throw new AdapterValidationException("Unable to deserialize tweet (expected-type = 'gnip-2', length = %s):\n\t%s", text.length(), text);
			}
		}
	}
}
