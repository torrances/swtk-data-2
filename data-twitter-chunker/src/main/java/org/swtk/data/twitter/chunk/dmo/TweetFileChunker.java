package org.swtk.data.twitter.chunk.dmo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.data.twitter.core.dmo.TransformGnipTweet;
import org.swtk.data.twitter.core.dto.canonical.CannonicalTweet;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class TweetFileChunker {

	public static LogManager logger = new LogManager(TweetFileChunker.class);

	private int process(File inputFile, Set<String> set, int lineCounter, int min, int max) throws BusinessException {
		try {

			try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
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

		} catch (IOException e) {
			logger.error(e);
			throw new BusinessException("I/O Exception reading input file (path = %s)", inputFile);
		}
		
		return lineCounter;
	}
	
	public Set<String> process(Collection<File> inputFiles, int min, int max) throws BusinessException {

		int lineCounter = 0;
		Set<String> set = new TreeSet<String>();

		for (File inputFile : inputFiles)
			lineCounter = process(inputFile, set, lineCounter, min, max);
		
		return set;
	}
}
