package org.swtk.data.drwho.scrape.svc;

import java.io.File;

import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class S03_PostProcess {

	public static void main(String... args) throws Throwable {

		StringBuilder sb = new StringBuilder();
		for (File file : FileUtils.getFilesFromPath("/Users/craigtrim/Desktop/drwho/dat/02/")) {
			for (String line : FileUtils.toList(file, Codepage.UTF_8)) {

				line = line.toLowerCase();
				if (line.startsWith("(")) continue;
				if (line.startsWith("[")) continue;
				line = line.replaceAll("\\(.*\\)", "");
				line = line.replaceAll("\\[.*\\]", "");

				line = TextUtils.removePunctuationExcept(line, '.', '\'', ' ', '-');

				line = StringUtils.replace(line, "'s", "");

				line = StringUtils.replace(line, "daleks", "dalek");
				line = StringUtils.replace(line, "doctor's", "dr-who");
				line = StringUtils.replace(line, "doctor", "dr-who");
				line = StringUtils.replace(line, "dr-who donna", "dr-donna");

				sb.append(line + " ");
			}

			sb.append(System.lineSeparator());
			sb.append(System.lineSeparator());
		}

		FileUtils.toFile(sb, "/Users/craigtrim/Desktop/drwho/dat/03/drwho004.dat", Codepage.UTF_8);
	}
}
