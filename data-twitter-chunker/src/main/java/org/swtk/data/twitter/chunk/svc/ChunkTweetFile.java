package org.swtk.data.twitter.chunk.svc;

import java.io.File;
import java.util.Collection;
import java.util.Set;

import org.swtk.data.twitter.chunk.dmo.TweetFileChunker;
import org.swtk.data.twitter.chunk.dto.ChunkTweetFileContract;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class ChunkTweetFile {

	public static LogManager logger = new LogManager(ChunkTweetFile.class);

	private int getNumberOfLines(Collection<File> files) throws BusinessException {
		int totalNumberOfLines = 0;
		for (File file : files)
			totalNumberOfLines += FileUtils.numberOfLines(file);

		logger.info("Read File (number-of-lines = %s, number-of-files = %s)", StringUtils.format(totalNumberOfLines), StringUtils.format(files.size()));
		return totalNumberOfLines;
	}

	public void process(ChunkTweetFileContract chunkTweetFileContract) throws BusinessException {

		final int numberOfLines = getNumberOfLines(chunkTweetFileContract.getInputFiles());
		for (int i = 0; i < numberOfLines; i += chunkTweetFileContract.getNumberOfTweetsPerFile()) {

			int min = i;
			int max = i + chunkTweetFileContract.getNumberOfTweetsPerFile();

			Set<String> tweets = new TweetFileChunker().process(chunkTweetFileContract.getInputFiles(), min, max);

			String name = getOutputFileName(chunkTweetFileContract);
			String sMin = StringUtils.pad(min, 8);
			String sMax = StringUtils.pad(max, 8);
			String outFileName = String.format("%s-%s-%s.dat", name, sMin, sMax);
			String outFilePath = String.format("%s/%s", chunkTweetFileContract.getOutputFileDirectory().getPath(), outFileName);

			logger.info("Writing to file ... (actual-lines = %s, out-file-path = %s)", tweets.size(), outFilePath);
			FileUtils.toFile(tweets, outFilePath, Codepage.UTF_8);
		}
	}

	private String getOutputFileName(ChunkTweetFileContract chunkTweetFileContract) throws BusinessException {
		StringBuilder sb = new StringBuilder();

		for (File file : chunkTweetFileContract.getInputFiles())
			sb.append(FileUtils.getName(file));

		return String.valueOf(sb.toString().hashCode());
	}
}
