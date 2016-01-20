package org.swtk.data.twitter.core.dto.v1.adapter;

import org.swtk.data.twitter.core.dto.v1.TweetGnip;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.GsonUtils;

public final class TweetGnipAdapter {

	public static LogManager logger = new LogManager(TweetGnipAdapter.class);

	public static TweetGnip transform(String text) throws AdapterValidationException {
		try {
			return GsonUtils.toObject(text, TweetGnip.class);

		} catch (Exception e) {
			logger.error(e);
			throw new AdapterValidationException("Unable to deserialize tweet (expected-type = 'gnip', length = %s)", text.length());
		}
	}
}
