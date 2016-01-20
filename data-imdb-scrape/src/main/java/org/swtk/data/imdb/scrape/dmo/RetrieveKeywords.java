package org.swtk.data.imdb.scrape.dmo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.imdb.json.dto.adapter.KeywordAdapter;
import org.swtk.data.imdb.json.dto.adapter.iterable.KeywordsAdapter;
import org.swtk.data.imdb.json.dto.iterable.Keywords;
import org.swtk.data.imdb.scrape.dto.SavePageResult;
import org.swtk.data.imdb.scrape.svc.SavePage;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RetrieveKeywords {

	public static LogManager logger = new LogManager(RetrieveKeywords.class);

	public static final String URL_TEMPLATE = "http://www.imdb.com/title/%s/keywords";

	private Keywords parse(Document doc) throws BusinessException {
		Keywords keywords = new Keywords();

		for (Element element : doc.getElementsByAttributeValue("class", "sodatext")) {
			String text = element.text();

			for (Element voteElement : element.nextElementSibling().getElementsByAttributeValue("class",
					"interesting-count-text")) {

				int totalVotes = 0;
				int positiveVotes = 0;
				String voteText = voteElement.text();

				if (!voteText.contains("Is this relevant")) {
					voteText = StringUtils.substringBefore(voteText, "found this relevant").trim();
					positiveVotes = Integer.parseInt(StringUtils.substringBefore(voteText, " of "));
					totalVotes = Integer.parseInt(StringUtils.substringAfter(voteText, " of "));
				}

				keywords.add(KeywordAdapter.transform(text, positiveVotes, totalVotes));
			}
		}

		return keywords;
	}

	public Keywords process(String id, String base) throws BusinessException {

		String URL = String.format(URL_TEMPLATE, id);
		SavePageResult savePageResult = new SavePage().process(URL, id, base, "keywords");

		Keywords keywords = parse(savePageResult.getDoc());

		if (keywords.isEmpty()) {
			logger.trace("No Results Found (id = %s", id);
			return keywords;
		}

		String results = KeywordsAdapter.toTsv(keywords);
		FileUtils.toFile(results, savePageResult.getOutDat().getAbsolutePath(), Codepage.UTF_8);

		return keywords;
	}
}
