package org.swtk.data.wiktionary.parser.svc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.MathUtils;
import com.trimc.blogger.commons.utils.Stopwatch;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class CreatePageFiles {

	public static LogManager logger = new LogManager(CreatePageFiles.class);

	public static final String OUT_DIR_05 = "/Users/craigtrim/Desktop/wiktionary/03/%s/%s/%s/%s/%s/%s";

	public static final String OUT_DIR_04 = "/Users/craigtrim/Desktop/wiktionary/03/%s/%s/%s/%s/%s";

	public static final String OUT_DIR_03 = "/Users/craigtrim/Desktop/wiktionary/03/%s/%s/%s/%s";

	private static String getDir03(String title) {
		String t1 = title.substring(0, 1);
		String t2 = title.substring(1, 2);
		String t3 = title.substring(2, 3);
		return String.format(OUT_DIR_03, t1, t2, t3, title);
	}

	private static String getDir04(String title) {
		String t1 = title.substring(0, 1);
		String t2 = title.substring(1, 2);
		String t3 = title.substring(2, 3);
		String t4 = title.substring(3, 4);
		return String.format(OUT_DIR_04, t1, t2, t3, t4, title);
	}

	private static String getDir05(String title) {
		String t1 = title.substring(0, 1);
		String t2 = title.substring(1, 2);
		String t3 = title.substring(2, 3);
		String t4 = title.substring(3, 4);
		String t5 = title.substring(4, 5);
		return String.format(OUT_DIR_05, t1, t2, t3, t4, t5, title);
	}

	public static void main(String... args) throws Exception {

		File file = new File("/Users/craigtrim/Downloads/enwiktionary-20151002-pages-articles.xml");

		int counter = 0;
		Stopwatch timer = new Stopwatch();

		String title = "unknown";
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);

				if (line.trim().startsWith("<title>")) {
					title = StringUtils.substringAfter(line, "<title>");
					title = StringUtils.substringBefore(title, "</title>");
					title = StringUtils.unicodeNormalization(StringUtils.trim(title).toLowerCase());
				}

				if ("</page>".equals(line.trim())) {
					if (TextUtils.isAlphaOnly(title)) {
						counter++;
						if (title.length() > 5) writeToFile(sb, getDir05(title));
						else if (title.length() > 4) writeToFile(sb, getDir04(title));
						else if (title.length() > 3) writeToFile(sb, getDir03(title));
					}

					sb = new StringBuilder();
				}
			}
		}

		logger.info("Serialized Files (total-files = %s, total-time = %s)", counter, timer.getTotalTime());
	}

	private static void writeToFile(StringBuilder sb, String outname) {
		try {

			String tid = String.valueOf(System.currentTimeMillis());
			String rid = String.valueOf(MathUtils.random(MathUtils.random(100, 30000), MathUtils.random(30001, Integer.MAX_VALUE)));
			String id = tid.substring(tid.length() - 5, tid.length()) + rid;
			outname = outname + "-" + id + ".txt";
			FileUtils.toFile(sb, outname, Codepage.UTF_8);
			logger.info("Write to File (path = %s)", outname);
		} catch (Exception e) {}
	}
}
