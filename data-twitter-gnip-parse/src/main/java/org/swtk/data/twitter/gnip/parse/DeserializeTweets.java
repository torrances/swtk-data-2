package org.swtk.data.twitter.gnip.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.data.twitter.core.dmo.TransformGnipTweet;
import org.swtk.data.twitter.core.dto.canonical.CannonicalTweet;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class DeserializeTweets {

	public static LogManager logger = new LogManager(DeserializeTweets.class);

	public Set<String> process(int min, int max) throws Throwable {

		int lineCounter = 0;
		Set<String> set = new TreeSet<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(new File("/Users/craigtrim/data/Data/twitter/20160119_0644.dat")))) {
			String line;

			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {

				sb.append(StringUtils.trim(line));
				if (!line.endsWith("}")) continue;

				String strTweet = StringUtils.trim(sb.toString());
				sb = new StringBuilder();

				lineCounter++;
				if (min > lineCounter || max < lineCounter) continue;

				try {

					CannonicalTweet tweet = new TransformGnipTweet().transform(strTweet);
					String text = tweet.getNormalizedNoHashtagsOrURLs();
					
					if (text.length() < 0) continue;
					if (!StringUtils.hasValue(text)) continue;

					set.add(text);

				} catch (Exception e) {
					logger.error(e, line);
					throw new BusinessException("Unable to deserialize line:\n\t%s", sb.toString());
				}
			}
		}
		
		return set;
	}
}
