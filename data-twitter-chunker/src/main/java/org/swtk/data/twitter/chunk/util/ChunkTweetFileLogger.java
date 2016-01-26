package org.swtk.data.twitter.chunk.util;

import java.io.File;
import java.util.Collection;
import java.util.Set;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.Stopwatch;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class ChunkTweetFileLogger {
	
	public static String fileReductionResults(File file, int actualLines) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("File Reduction Results:");
		sb.append(String.format("\n\toriginal-number-of-lines:		%s", StringUtils.format(FileUtils.numberOfLines(file))));
		sb.append(String.format("\n\tmodified-number-of-lines:		%s", StringUtils.format(actualLines)));
		
		return sb.toString();
	}
	
	public static String numberOfLines(Collection<File> files, long totalNumberOfLines, Stopwatch timer) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Read File:");
		sb.append(String.format("\n\tnumber-of-files = %s", StringUtils.format(files.size())));
		sb.append(String.format("\n\tnumber-of-lines = %s", StringUtils.format(totalNumberOfLines)));
		sb.append(String.format("\n\ttime-to-read = %s", timer.getTotalTime()));
		
		return sb.toString();
	}

	public static String writingToFile(Set<String> tweets, int min, int max, String outFilePath, Stopwatch timer) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Writing to file:");
		sb.append(String.format("\n\tactual-lines:				%s", StringUtils.format(tweets.size())));
		sb.append(String.format("\n\tmin:					%s", StringUtils.format(min)));
		sb.append(String.format("\n\tmax:					%s", ((Integer.MAX_VALUE == max) ? "*" : StringUtils.format(max))));
		sb.append(String.format("\n\tout-file-path:				%s", outFilePath));
		sb.append(String.format("\n\tttotal-elapsed-time:			%s", timer.getTotalTime()));
		
		return sb.toString();
	}
}
