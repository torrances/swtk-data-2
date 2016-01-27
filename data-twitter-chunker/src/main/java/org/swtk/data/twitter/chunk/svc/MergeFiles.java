package org.swtk.data.twitter.chunk.svc;

import java.io.File;
import java.util.Collection;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.Stopwatch;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class MergeFiles {

	public static LogManager logger = new LogManager(MergeFiles.class);

	private static int id(int ctr) {
		return String.valueOf(System.currentTimeMillis() + ctr).hashCode();
	}

	public static void main(String... args) throws Throwable {
		mergeIntoSingleFile(
				"/Users/craigtrim/data/Data/twitter/output-2/", 
				"dat", 
				Codepage.UTF_8, 
				"/Users/craigtrim/workspaces/public/python/MinHash/input/", 
				32768);
	}

	protected static void mergeIntoSingleFile(String foldername, String ext, Codepage codepage, String outputTemplate, int increment) throws BusinessException {
		StringBuilder sb = new StringBuilder();

		Stopwatch master = new Stopwatch();
		Collection<File> files = FileUtils.getDescendantFilesInFolder(foldername, ext);

		int counter = 1;
		final String TOTAL = StringUtils.format(files.size());

		for (File file : files) {
			logger.debug("Processing File (name = %s, %s - %s, elapsed-time = %s)", FileUtils.getName(file), StringUtils.format(counter++), TOTAL, master.getTotalTime());

			for (String line : FileUtils.toList(file, codepage)) {
				counter++;

				if (0 == counter % increment) {
					writeToFile(sb, outputTemplate, counter);
					sb = new StringBuilder();
				}

				sb.append(id(counter) + " " + line);
				sb.append(System.lineSeparator());
			}
		}

		writeToFile(sb, outputTemplate, counter);
	}

	private static void writeToFile(StringBuilder sb, String outputTemplate, int counter) throws BusinessException {
		String outputFilePath = outputTemplate + "M" + StringUtils.pad(counter, 10) + ".dat";
		FileUtils.toFile(sb, outputFilePath, true, Codepage.UTF_8);
		logger.debug("Created Incremental File (path = %s)", outputFilePath);
	}
}
