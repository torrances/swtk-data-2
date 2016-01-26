package org.swtk.data.twitter.chunk.svc;

import java.io.File;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.data.twitter.chunk.dmo.TweetFileChunker;
import org.swtk.data.twitter.chunk.dto.ChunkTweetFileContract;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.Stopwatch;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class ChunkTweetFile {

	public static LogManager logger = new LogManager(ChunkTweetFile.class);

	private long getNumberOfLines(Collection<File> files) throws BusinessException {
		Stopwatch timer = new Stopwatch();

		long totalNumberOfLines = 0;
		for (File file : files)
			totalNumberOfLines += FileUtils.numberOfLines(file);

		logger.info("Read File (number-of-files = %s, number-of-lines = %s, time-to-read = %s)", StringUtils.format(files.size()), StringUtils.format(totalNumberOfLines), timer.getTotalTime());
		return totalNumberOfLines;
	}

	private String getOutputFileName(ChunkTweetFileContract chunkTweetFileContract) throws BusinessException {
		StringBuilder sb = new StringBuilder();

		for (File file : chunkTweetFileContract.getInputFiles())
			sb.append(FileUtils.getName(file));

		return String.valueOf(sb.toString().hashCode());
	}

	public void process(ChunkTweetFileContract contract) throws BusinessException {

		final long numberOfLines = getNumberOfLines(contract.getInputFiles());

		for (File file : contract.getInputFiles()) {
			logger.info("Processing File: %s", file.getAbsolutePath());

			int min = 0, max = 0;
			Set<String> set = new TreeSet<String>();

			for (int i = 0; i < numberOfLines; i += contract.getNumberOfTweetsPerFile()) {

				min = i;
				max = i + contract.getNumberOfTweetsPerFile();

				int linePosition = new TweetFileChunker().process(file, set, min, max, contract.getNumberOfTweetsPerFile(), contract.getTargetFormat());
				if (-1 == linePosition || set.size() >= contract.getNumberOfTweetsPerFile()) {
					writeToFile(contract, set, min, max);
					set = new TreeSet<String>();
				}
			}

			writeToFile(contract, set, min, max);
		}
	}

	private void writeToFile(ChunkTweetFileContract chunkTweetFileContract, Set<String> tweets, int min, int max) throws BusinessException {

		String name = getOutputFileName(chunkTweetFileContract);
		String sMin = StringUtils.pad(min, 8);
		String sMax = StringUtils.pad(max, 8);
		String outFileName = String.format("%s-%s-%s.dat", name, sMin, sMax);
		String outFilePath = String.format("%s/%s", chunkTweetFileContract.getOutputFileDirectory().getPath(), outFileName);

		if (tweets.isEmpty()) return;

		logger.info("Writing to file ... (actual-lines = %s, min = %s, max = %s, out-file-path = %s)", StringUtils.format(tweets.size()), StringUtils.format(min), ((Integer.MAX_VALUE == max) ? "*" : StringUtils.format(max)), outFilePath);

		FileUtils.toFile(tweets, outFilePath, Codepage.UTF_8);
	}
}
