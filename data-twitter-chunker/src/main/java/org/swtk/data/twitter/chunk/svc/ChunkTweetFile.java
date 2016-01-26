package org.swtk.data.twitter.chunk.svc;

import java.io.File;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.data.twitter.chunk.dmo.TweetFileChunker;
import org.swtk.data.twitter.chunk.dto.ChunkTweetFileContract;
import org.swtk.data.twitter.chunk.util.ChunkTweetFileLogger;

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

		logger.info("%s", ChunkTweetFileLogger.numberOfLines(files, totalNumberOfLines, timer));
		return totalNumberOfLines;
	}

	public void process(ChunkTweetFileContract contract) throws BusinessException {

		Stopwatch timer = new Stopwatch();
		final long numberOfLines = getNumberOfLines(contract.getInputFiles());

		for (File file : contract.getInputFiles()) {
			logger.debug("Processing File: %s", file.getAbsolutePath());

			int actualLines = 0;
			int min = 0, max = 0;
			Set<String> set = new TreeSet<String>();

			for (int i = 0; i < numberOfLines; i += contract.getNumberOfTweetsPerFile()) {

				min = i;
				max = i + contract.getNumberOfTweetsPerFile();

				int linePosition = new TweetFileChunker().process(file, set, min, max, contract.getNumberOfTweetsPerFile(), contract.getTargetFormat());
				if (-1 == linePosition || set.size() >= contract.getNumberOfTweetsPerFile()) {
					actualLines += set.size();
					writeToFile(file, contract, set, min, max, timer);
					set = new TreeSet<String>();
				}
			}

			if (set.size() > 1) writeToFile(file, contract, set, min, max, timer);
			logger.info("%s", ChunkTweetFileLogger.fileReductionResults(file, actualLines));
		}
	}

	private void writeToFile(File currentFile, ChunkTweetFileContract chunkTweetFileContract, Set<String> tweets, int min, int max, Stopwatch timer) throws BusinessException {

		String name = FileUtils.getName(currentFile);
		String sMin = StringUtils.pad(min, 8);
		String sMax = StringUtils.pad(max, 8);
		String outFileName = String.format("%s-%s-%s.dat", name, sMin, sMax);
		String outFilePath = String.format("%s/%s", chunkTweetFileContract.getOutputFileDirectory().getPath(), outFileName);

		if (tweets.isEmpty()) return;

		logger.info("%s", ChunkTweetFileLogger.writingToFile(tweets, min, max, outFilePath, timer));
		FileUtils.toFile(tweets, outFilePath, Codepage.UTF_8);
	}
}
