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

		logger.info("Read File:\n\tnumber-of-files = %s\n\tnumber-of-lines = %s\n\ttime-to-read = %s", StringUtils.format(files.size()), StringUtils.format(totalNumberOfLines), timer.getTotalTime());
		return totalNumberOfLines;
	}

	private String getOutputFileName(ChunkTweetFileContract chunkTweetFileContract) throws BusinessException {
		StringBuilder sb = new StringBuilder();

		for (File file : chunkTweetFileContract.getInputFiles())
			sb.append(FileUtils.getName(file));

		return String.valueOf(sb.toString().hashCode());
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
				//				if (-1 != linePosition) continue;
				//				if (set.size() < contract.getNumberOfTweetsPerFile()) continue;
				if (-1 == linePosition || set.size() >= contract.getNumberOfTweetsPerFile()) {
					actualLines += set.size();
					writeToFile(contract, set, min, max, timer);
					set = new TreeSet<String>();
				}
			}

			writeToFile(contract, set, min, max, timer);
			logger.info("File Reduction Results:\n\toriginal-number-of-lines = %s\n\tmodified-number-of-lines = %s", FileUtils.numberOfLines(file), actualLines);
		}
	}

	private void writeToFile(ChunkTweetFileContract chunkTweetFileContract, Set<String> tweets, int min, int max, Stopwatch timer) throws BusinessException {

		String name = getOutputFileName(chunkTweetFileContract);
		String sMin = StringUtils.pad(min, 8);
		String sMax = StringUtils.pad(max, 8);
		String outFileName = String.format("%s-%s-%s.dat", name, sMin, sMax);
		String outFilePath = String.format("%s/%s", chunkTweetFileContract.getOutputFileDirectory().getPath(), outFileName);

		if (tweets.isEmpty()) return;

		StringBuilder sb = new StringBuilder();
		sb.append("Writing to file:");
		sb.append(String.format("\n\tactual-lines:						%s", StringUtils.format(tweets.size())));
		sb.append(String.format("\n\tmin:								%s", StringUtils.format(min)));
		sb.append(String.format("\n\tmax:								%s", ((Integer.MAX_VALUE == max) ? "*" : StringUtils.format(max))));
		sb.append(String.format("\n\tout-file-path:						%s", outFilePath));
		sb.append(String.format("\n\tttotal-elapsed-time:				%s", timer.getTotalTime()));
		logger.info("%s", sb.toString());

		FileUtils.toFile(tweets, outFilePath, Codepage.UTF_8);
	}
}
