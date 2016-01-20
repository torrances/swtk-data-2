package org.swtk.data.imdb.scrape.dmo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.imdb.json.dto.UserRating;
import org.swtk.data.imdb.json.dto.adapter.UserRatingAdapter;
import org.swtk.data.imdb.json.dto.adapter.iterable.UserRatingsAdapter;
import org.swtk.data.imdb.json.dto.iterable.UserRatings;
import org.swtk.data.imdb.scrape.dto.SavePageResult;
import org.swtk.data.imdb.scrape.svc.SavePage;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RetrieveUserRatings {

	public static LogManager logger = new LogManager(RetrieveUserRatings.class);

	public static final String[] RATING_TYPES = {};

	public static Map<String, String> ratingsTypeMap = new HashMap<String, String>();

	static {
		ratingsTypeMap.put("ratings-male", "males");
		ratingsTypeMap.put("ratings-female", "females");
		ratingsTypeMap.put("ratings-age_1", "");
		ratingsTypeMap.put("ratings-male_age_1", "");
		ratingsTypeMap.put("ratings-female_age_1", "");
		ratingsTypeMap.put("ratings-age_2", "");
		ratingsTypeMap.put("ratings-male_age_2", "");
		ratingsTypeMap.put("ratings-female_age_2", "");
		ratingsTypeMap.put("ratings-age_3", "");
		ratingsTypeMap.put("ratings-male_age_3", "");
		ratingsTypeMap.put("ratings-female_age_3", "");
		ratingsTypeMap.put("ratings-age_4", "");
		ratingsTypeMap.put("ratings-male_age_4", "");
		ratingsTypeMap.put("ratings-female_age_4", "");
		ratingsTypeMap.put("ratings-imdb_staff", "");
		ratingsTypeMap.put("ratings-top_1000", "");
		ratingsTypeMap.put("ratings-international", "");
		ratingsTypeMap.put("ratings-usa", "");
	}

	public static final String URL_TEMPLATE = "http://www.imdb.com/title/%s/ratings";

	private UserRating getTotalUserRating(Document doc) throws BusinessException {

		String totalUsers = null;
		String userRating = doc.getElementsByAttributeValueContaining("href", "/search/title?user_rating").first()
				.text();

		for (Element paragraph : doc.getElementsByTag("p")) {
			if (!paragraph.text().contains("IMDb users have given a"))
				continue;
			totalUsers = StringUtils.substringBefore(paragraph.text(), "IMDb users have given a").trim();
		}

		return UserRatingAdapter.transform("total", totalUsers, userRating);
	}

	private UserRating getUserRating(Document doc, String type) throws BusinessException {
		Element ratingElement = doc.getElementsByAttributeValue("href", type).first();
		String votes = ratingElement.parent().nextElementSibling().text();
		String average = ratingElement.parent().nextElementSibling().nextElementSibling().text();

		String normalizedType = (type.startsWith("ratings-")) ? StringUtils.substringAfter(type, "ratings-") : type;
		return UserRatingAdapter.transform(normalizedType, votes, average);
	}

	private UserRatings parse(final String ID, Document doc) throws BusinessException {
		UserRatings userRatings = new UserRatings();
		userRatings.add(getTotalUserRating(doc));

		List<String> errors = new ArrayList<String>();
		for (String ratingType : RATING_TYPES) {
			try {
				userRatings.add(getUserRating(doc, ratingType));
			} catch (Exception e) {
				errors.add(ratingType);
			}
		}

		if (!errors.isEmpty()) {
			logger.error("Unable to retrieve the following rating-types (id = %s, values = %s)", ID,
					StringUtils.toString(errors, ", "));
		}

		return userRatings;
	}

	public UserRatings process(String id, String base) throws BusinessException {

		String URL = String.format(URL_TEMPLATE, id);
		SavePageResult savePageResult = new SavePage().process(URL, id, base, "ratings");

		UserRatings userRatings = parse(id, savePageResult.getDoc());
		if (userRatings.isEmpty()) {
			logger.trace("No Results Found (id = %s", id);
			return userRatings;
		}

		String results = UserRatingsAdapter.toTsv(userRatings);
		FileUtils.toFile(results, savePageResult.getOutDat().getAbsolutePath(), Codepage.UTF_8);

		return userRatings;
	}
}
