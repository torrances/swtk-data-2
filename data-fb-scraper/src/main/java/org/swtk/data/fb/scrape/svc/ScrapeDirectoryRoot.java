package org.swtk.data.fb.scrape.svc;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.common.framework.type.Alpha;
import org.swtk.data.fb.scrape.dmo.PageRetriever;
import org.swtk.data.fb.scrape.util.Constants;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class ScrapeDirectoryRoot {

	public static LogManager logger = new LogManager(ScrapeDirectoryRoot.class);

	public static void main(String... args) throws Throwable {

		File BASE_OUT_DIR_FILE = new File(Constants.BASE_OUT_DIR);
		if (!BASE_OUT_DIR_FILE.exists()) BASE_OUT_DIR_FILE.mkdirs();

		for (Alpha alpha : Alpha.values()) {

			File tmp = new File(BASE_OUT_DIR_FILE.getAbsolutePath() + String.format("/%s.html", alpha.lower()));
			PageRetriever.get("https://www.facebook.com/directory/people/" + alpha.upper(), tmp);

			// https://www.facebook.com/directory/people/A-1-1556640
			Document doc = Jsoup.parse(tmp, Codepage.UTF_8.getCharset().toString());
			for (Element e : doc.getElementsByAttributeValueStarting("href", "https://www.facebook.com/directory/people/")) {

				String href = e.attr("href");
				String next = StringUtils.substringAfter(href, "people/").trim();

				if (!StringUtils.hasValue(next)) continue;
				if (!next.contains("-")) continue;

				File tmp1 = new File(BASE_OUT_DIR_FILE.getAbsolutePath() + "/" + alpha.lower() + "/" + next + ".html");
				try {
					PageRetriever.get(href, tmp1);
				} catch (BusinessException e2) {
					return;
				}
			}
		}
	}
}
