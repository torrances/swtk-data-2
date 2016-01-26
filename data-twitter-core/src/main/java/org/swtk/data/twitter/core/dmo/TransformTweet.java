package org.swtk.data.twitter.core.dmo;

import java.util.Set;
import java.util.TreeSet;

import org.swtk.data.twitter.core.dto.canonical.CannonicalTweet;
import org.swtk.data.twitter.core.dto.gnip.GnipTweet;
import org.swtk.data.twitter.core.dto.gnip.adapter.GnipTweetAdapter;
import org.swtk.eng.tokenizer.text.TextTokenizer;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class TransformTweet {

	public static LogManager logger = new LogManager(TransformTweet.class);

	public CannonicalTweet toCannonicalForm(GnipTweet tweetGnip) throws BusinessException {
		CannonicalTweet tweet = new CannonicalTweet();

		tweet.setText(StringUtils.trim(tweetGnip.getBody()));
		tweet.setUser(tweetGnip.getActor().getPreferredUsername());

		String normalized = tweet.getText().toLowerCase();
		normalized = TextUtils.removePunctuationExcept(normalized, ' ');
		normalized = TextUtils.removeSpecialCharacters(normalized);

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

	public CannonicalTweet toCannonicalForm(String text) throws BusinessException {
		try {

			/*	GNIP format	*/
			if (text.startsWith("{\"id\":\"")) {
				return toCannonicalForm(toGnipForm(text));
			}

			return GsonUtils.toObject(text, CannonicalTweet.class);

		} catch (Exception e) {
			logger.error(e);
			throw new BusinessException("Unable to deserialize tweet (target-format = 'cannonical', length = %s)", text.length());
		}
	}

	public GnipTweet toGnipForm(String text) throws BusinessException {
		try {

			/*	GNIP format	*/
			if (text.startsWith("{\"id\":\"")) {
				return GnipTweetAdapter.transform(text);
			}
			
		} catch (Exception e) {
			logger.error(e);
		}

		throw new BusinessException("Unable to deserialize tweet (target-format = 'gnip', length = %s)", text.length());
	}
}
