package org.swtk.data.rt.scrape.svc;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.swtk.data.rt.scrape.dmo.PageRetriever;

import com.trimc.blogger.commons.type.Codepage;

public class GenericScrapeIdea {

	public static void main(String... args) throws Throwable {

		String url = "http://www.avclub.com/review/the-lord-of-the-rings-the-return-of-the-king-5296";
		File out = new File("/Users/craigtrim/data/Data/rt/" + url.hashCode() + ".html");

		if (!out.exists()) PageRetriever.get(url, out);
		
		Document doc = Jsoup.parse(out, Codepage.UTF_8.getCharset().toString());
		String text = doc.getElementsByAttributeValueContaining("class", "article-text").first().text();
		
		System.err.println(text);
	}
}
