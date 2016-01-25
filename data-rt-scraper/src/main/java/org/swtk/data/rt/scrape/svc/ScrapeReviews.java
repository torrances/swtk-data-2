package org.swtk.data.rt.scrape.svc;

import java.io.File;

import org.swtk.data.rt.scrape.dmo.PageRetriever;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class ScrapeReviews {

	public static final String URL_BASE_REVIEWS = "http://www.rottentomatoes.com/m/%s/reviews/?page=%s";

	public static void main(String... args) throws Throwable {
		String title = "the_lord_of_the_rings_the_return_of_the_king";
		process(title);
	}

	public static void process(String title) throws BusinessException {

		File dir = new File("/Users/craigtrim/data/Data/rt/" + title + "/reviews/");
		if (!dir.exists()) dir.mkdirs();

		for (int i = 0; i < 15; i++) {

			File out = new File(dir.getAbsolutePath() + "/" + StringUtils.pad(i, 3) + ".html");
			if (out.exists()) continue;

			String url = String.format(URL_BASE_REVIEWS, title, i);
			PageRetriever.get(url, out);
		}
	}
}
