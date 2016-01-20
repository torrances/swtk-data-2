package org.swtk.data.twitter.core.dmo;

import java.util.Set;
import java.util.TreeSet;

import org.swtk.data.twitter.core.dto.generic.Tweet;
import org.swtk.data.twitter.core.dto.v1.TweetGnip;
import org.swtk.data.twitter.core.dto.v1.adapter.TweetGnipAdapter;
import org.swtk.eng.tokenizer.text.TextTokenizer;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class TransformGnipTweet {

	public static LogManager logger = new LogManager(TransformGnipTweet.class);

	public Tweet transform(String text) throws BusinessException {
		try {

			/*	GNIP format	*/
			if (text.startsWith("{\"id\":\"")) {
				return transform(TweetGnipAdapter.transform(text));
			}

			return GsonUtils.toObject(text, Tweet.class);

		} catch (Exception e) {
			logger.error(e);
			throw new AdapterValidationException("Unable to deserialize tweet (expected-type = 'generic', length = %s)", text.length());
		}
	}

	public Tweet transform(TweetGnip tweetGnip) throws BusinessException {
		Tweet tweet = new Tweet();

		tweet.setText(StringUtils.trim(tweetGnip.getBody()));
		tweet.setUser(tweetGnip.getActor().getPreferredUsername());

		String normalized = tweet.getText().toLowerCase();
		normalized = TextUtils.removePunctuationExcept(normalized, ' ');
		normalized = TextUtils.removeSpecialCharactersExcept(normalized, ' ');

		Set<String> hashtags = new TreeSet<String>();

		StringBuilder sb = new StringBuilder();
		for (String token : new TextTokenizer(normalized).tokenize().array()) {

			if (token.startsWith("@")) {
				hashtags.add(token);
				continue;
			} else if (token.startsWith("http")) {
				continue;
			}

			sb.append(token + " ");
		}

		tweet.setNormalized(normalized);
		tweet.setHashtags(hashtags);
		tweet.setNormalizedNoHashtagsOrURLs(StringUtils.trim(sb.toString()));

		return tweet;
	}
}
