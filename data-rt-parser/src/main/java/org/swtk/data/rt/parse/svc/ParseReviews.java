package org.swtk.data.rt.parse.svc;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.swtk.data.rt.core.dto.Critic;
import org.swtk.data.rt.core.dto.Review;
import org.swtk.data.rt.core.dto.Score;
import org.swtk.data.rt.core.dto.adapter.CriticAdapter;
import org.swtk.data.rt.core.dto.adapter.ReviewAdapter;
import org.swtk.data.rt.core.dto.adapter.ScoreAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class ParseReviews {

	public static LogManager logger = new LogManager(ParseReviews.class);

	private static String getBlurb(Element e) {
		Element match = e.getElementsByAttributeValueContaining("class", "the_review").first();
		return match.text().trim();
	}

	private static Critic getCritic(Element e) throws BusinessException {
		Element match = e.getElementsByAttributeValueContaining("class", "critic_name").first();
		if (null == match) return null;

		if (0 == match.getElementsByTag("a").size()) return null;
		if (0 == match.getElementsByTag("em").size()) return null;

		String name = match.getElementsByTag("a").first().text();
		String company = match.getElementsByTag("em").first().text();

		return CriticAdapter.transform(name, company);
	}

	private static String getDate(Element e) {
		Element match = e.getElementsByAttributeValueContaining("class", "review_date").first();
		return match.text().trim();
	}

	private static String getFullReviewLink(Element e) {
		Element match = e.getElementsContainingOwnText("Full Review").first();
		return match.attr("href");
	}

	private static Score getOriginalScore(Element e) throws BusinessException {
		Elements matches = e.getElementsContainingOwnText("Original Score");
		if (null == matches || 0 == matches.size()) return null;

		String text = matches.first().text();
		text = StringUtils.substringAfter(text, "Original Score:");

		String min = StringUtils.substringBefore(text, "/").trim();
		String max = StringUtils.substringAfter(text, "/").trim();

		return ScoreAdapter.transform(min, max);
	}

	public static void main(String... args) throws Throwable {
		process();
	}

	public static void process() throws Exception {

		File file = new File("/Users/craigtrim/data/Data/rt/the_lord_of_the_rings_the_return_of_the_king/reviews/001.html");
		Document doc = Jsoup.parse(file, Codepage.UTF_8.getCharset().toString());

		for (Element e : doc.getElementsByAttributeValueContaining("class", "review_table_row")) {
			Review review = new Review();
			review.setBlurb(getBlurb(e));
			review.setDate(getDate(e));
			review.setCritic(getCritic(e));
			review.setFullReviewLink(getFullReviewLink(e));
			review.setScore(getOriginalScore(e));
			logger.debug("%s", ReviewAdapter.toString(review));
		}

	}
}
