package org.swtk.data.twitter.core.utils;

import org.swtk.data.twitter.core.dto.v2.TweetV2;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.GsonUtils;

public final class TwitterSerializationFactory {

	public static LogManager logger = new LogManager(TwitterSerializationFactory.class);

	public static TweetV2 deserialize(String text) throws BusinessException {
		try {
			return GsonUtils.toObject(text, TweetV2.class);
		
		} catch (Exception e) {
			logger.error(e);
			throw new BusinessException("Unable to deserialize tweet (length = %s)", text.length());
		}
	}
}
