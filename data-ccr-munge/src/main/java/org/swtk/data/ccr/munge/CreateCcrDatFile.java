package org.swtk.data.ccr.munge;

import java.io.File;
import java.util.Collection;

import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class CreateCcrDatFile {

	public static void main(String... args) throws Throwable {

		StringBuilder sb = new StringBuilder();
		String name = "";

		Collection<String> grams = FileUtils.toList("/Users/craigtrim/Desktop/bigrams002.txt", Codepage.UTF_8);

		for (File file : FileUtils.getDescendantFilesInFolder("/Users/craigtrim/data/Data/ong/03/", "*")) {
			if (!"txt".equals(FileUtils.getExtension(file)))
				continue;

			String temp = StringUtils.substringBefore(FileUtils.getName(file), "_");
			if (!temp.equals(name)) {
				name = temp;
				sb.append(System.lineSeparator());
				sb.append(System.lineSeparator());
			}

			for (String line : FileUtils.toList(file, Codepage.UTF_16LE)) {

				line = line.toLowerCase();
				if (line.startsWith("("))
					continue;
				if (line.startsWith("["))
					continue;
				line = line.replaceAll("\\(.*\\)", "");
				line = line.replaceAll("\\[.*\\]", "");

				line = TextUtils.removePunctuationExcept(line, '.', '\'', ' ', '-');
				line = StringUtils.replace(line, "'s", "");

				for (String gram : grams)
					if (line.contains(gram)) {
						try {
							line = line.replaceAll(gram, gram.replaceAll(" ", "_"));
						} catch (Exception e) {
						}
					}

				sb.append(line);
				sb.append(System.lineSeparator());

				// System.err.println(line);

				break;
			}

			if (sb.length() > 50000) {
				FileUtils.toFile(sb, "/Users/craigtrim/Desktop/word2vec/ccr/dat/ccr006.dat", true, Codepage.UTF_8);
				sb = new StringBuilder();
			}

		}

		FileUtils.toFile(sb, "/Users/craigtrim/Desktop/word2vec/ccr/dat/ccr006.dat", true, Codepage.UTF_8);
	}
}
