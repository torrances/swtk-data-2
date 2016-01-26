package org.swtk.data.twitter.chunk.dmo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import org.swtk.data.twitter.core.dmo.TransformTweet;
import org.swtk.data.twitter.core.dto.canonical.CannonicalTweet;
import org.swtk.data.twitter.core.dto.gnip.adapter.GnipTweetAdapter;
import org.swtk.data.twitter.core.dto.type.TwitterFormat;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class TweetFileChunker {

	public static LogManager logger = new LogManager(TweetFileChunker.class);

	public int process(File inputFile, Set<String> set, int minLinePosition, int maxLinePosition, int totalTweetsPerFile, TwitterFormat targetFormat) throws BusinessException {
		logger.debug("\tInitiated Chunker (input-file = %s, current-set = %s, min-line-position = %s, max-line-position = %s)", inputFile.getAbsolutePath(), set.size(), minLinePosition, maxLinePosition);

		int linePosition = 0;
		try {

			try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
				String line;

				StringBuilder sb = new StringBuilder();
				while ((line = br.readLine()) != null) {

					linePosition++;

					if (linePosition < minLinePosition) continue;
					if (set.size() >= totalTweetsPerFile) return linePosition;

					sb.append(StringUtils.trim(line));
					if (!line.endsWith("}")) continue;

					String strTweet = StringUtils.trim(sb.toString());
					sb = new StringBuilder();

					if (TwitterFormat.CANNONICAL == targetFormat) {
						toCannonicalForm(strTweet, set);
					} else if (TwitterFormat.GNIP == targetFormat) {
						toGnip(strTweet, set);
					}
				}
			}

		} catch (IOException e) {
			logger.error(e);
			throw new BusinessException("I/O Exception reading input file (path = %s)", inputFile);
		}

		return -1;
	}

	private void toCannonicalForm(String strTweet, Set<String> set) throws BusinessException {
		try {

			CannonicalTweet tweet = new TransformTweet().toCannonicalForm(strTweet);
			String text = tweet.getNormalizedNoHashtagsOrURLs();

			if (text.length() < 0) return;
			if (!StringUtils.hasValue(text)) return;

			set.add(text);

		} catch (Exception e) {
			logger.error(e, strTweet);
			throw new BusinessException("Unable to deserialize line:\n\t%s", strTweet);
		}
	}

	private void toGnip(String strTweet, Set<String> set) throws BusinessException {
		set.add(GnipTweetAdapter.toString(new TransformTweet().toGnipForm(strTweet)));
	}

	//	public void process(ChunkTweetFileContract chunkTweetFileContract) throws BusinessException {
	//
	//		Set<String> set = new TreeSet<String>();
	//
	//		for (File inputFile : chunkTweetFileContract.getInputFiles()) {
	//			int linePosition = process(inputFile, set, chunkTweetFileContract.getNumberOfTweetsPerFile());
	//		}
	//
	//		//		return set;
	//	}
	//
	//	private String getOutputFileName(ChunkTweetFileContract chunkTweetFileContract) throws BusinessException {
	//		StringBuilder sb = new StringBuilder();
	//
	//		for (File file : chunkTweetFileContract.getInputFiles())
	//			sb.append(FileUtils.getName(file));
	//
	//		return String.valueOf(sb.toString().hashCode());
	//	}
}
