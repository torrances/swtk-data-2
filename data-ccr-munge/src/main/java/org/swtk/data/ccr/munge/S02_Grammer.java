package org.swtk.data.ccr.munge;

import java.io.File;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class S02_Grammer {

	public static LogManager logger = new LogManager(S02_Grammer.class);

	public static void main(String... args) throws Throwable {

		StringBuilder sb = new StringBuilder();
		File file = new File("/Users/craigtrim/Desktop/bigrams.txt");
		for (String line : FileUtils.toList(file, Codepage.UTF_8)) {
			String tok = StringUtils.substringAfterLast(line, ",").trim();
			int count = Integer.parseInt(tok.trim());
			if (count < 3)
				continue;
			sb.append(StringUtils.substringBefore(line, ",").trim() + "\n");
		}

		FileUtils.toFile(sb, "/Users/craigtrim/Desktop/bigrams002.txt", Codepage.UTF_8);
	}
}
