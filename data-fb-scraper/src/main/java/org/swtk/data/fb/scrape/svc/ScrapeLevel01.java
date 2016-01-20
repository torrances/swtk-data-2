package org.swtk.data.fb.scrape.svc;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.fb.scrape.dmo.PageRetriever;
import org.swtk.data.fb.scrape.util.Constants;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class ScrapeLevel01 {

	public static LogManager logger = new LogManager(ScrapeLevel01.class);

	public static void main(String... args) throws Throwable {

		File BASE_OUT_DIR_FILE = new File(Constants.BASE_OUT_DIR);
		if (!BASE_OUT_DIR_FILE.exists()) BASE_OUT_DIR_FILE.mkdirs();

		File base = new File("/Users/craigtrim/data/Data/fb/a/");
		for (File file : base.listFiles()) {

			String name = StringUtils.substringBefore(file.getName(), ".html");
			if (!StringUtils.hasValue(name)) continue;

			File in = new File("/Users/craigtrim/data/Data/fb/a/" + name + ".html");

			Document doc = Jsoup.parse(in, Codepage.UTF_8.getCharset().toString());
			for (Element e : doc.getElementsByTag("a")) {

				String href = e.attr("href");
				if (!href.startsWith("https://www.facebook.com/directory/people/A-")) continue;

				String next = StringUtils.substringAfter(href, "people/A-");
				next = "A-".concat(next);

				File dir = new File("/Users/craigtrim/data/Data/fb/a/" + name);
				if (!dir.exists()) dir.mkdirs();

				File out = new File(dir.getAbsolutePath() + "/" + next + ".html");
				try {
					PageRetriever.get(href, out);
				} catch (BusinessException ex) {
					return;
				}
			}
		}

	}
}
