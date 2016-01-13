package org.swtk.data.drwho.scrape.svc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.eng.preprocess.functions.CreateSentences;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class S02_Extract {

	public static LogManager logger = new LogManager(S02_Extract.class);

	public static void main(String... args) throws Throwable {

		for (int season = 0; season < 50; season++) {
			for (int episode = 0; episode < 50; episode++) {

				String input = StringUtils.pad(season, 2) + "_" + StringUtils.pad(episode, 2) + ".htm";
				String output = StringUtils.pad(season, 2) + "_" + StringUtils.pad(episode, 2) + ".txt";

				File file = new File("/Users/craigtrim/Desktop/drwho/01/" + input);
				if (!file.exists()) continue;

				Document doc = Jsoup.parse(FileUtils.toString(file, Codepage.UTF_8));
				for (Element table : doc.getElementsByTag("table")) {

					List<String> list1 = new ArrayList<String>();
					list1.add(table.text());

					List<String> list2 = new ArrayList<String>();
					for (String sentence : CreateSentences.process(list1)) {

						List<String> temp = new ArrayList<String>();
						temp.add(sentence);
						for (String s : CreateSentences.process(temp))
							list2.add(s);
					}

					List<String> list3 = new ArrayList<String>();
					for (String sentence : list2) {

						if (sentence.contains(":")) {
							String name = StringUtils.substringBefore(sentence, ":").trim();
							if (TextUtils.isUpperCase(name)) {
								sentence = StringUtils.substringAfter(sentence, ":").trim();
							}
						}

						list3.add(sentence);
					}

					File OUT = new File("/Users/craigtrim/Desktop/drwho/02/" + output);
					FileUtils.toFile(list3, OUT.getAbsolutePath(), Codepage.UTF_8);
				}
			}
		}
	}
}
