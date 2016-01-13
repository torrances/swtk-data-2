package org.swtk.data.imdb.scrape.dmo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.imdb.json.dto.Quotation;
import org.swtk.data.imdb.json.dto.adapter.QuoteAdapter;
import org.swtk.data.imdb.json.dto.adapter.iterable.QuotationsAdapter;
import org.swtk.data.imdb.json.dto.iterable.Quotations;
import org.swtk.data.imdb.scrape.dto.SavePageResult;
import org.swtk.data.imdb.scrape.svc.SavePage;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RetrieveQuotes {

	public static LogManager logger = new LogManager(RetrieveQuotes.class);

	public static final String URL_TEMPLATE = "http://www.imdb.com/title/%s/trivia?tab=qt";

	private String getCharacter(Element paragraph) {
		for (Element character : paragraph.getElementsByAttributeValue("class", "character"))
			return character.text();
		return null;
	}
	
	private Quotations parse(Document doc) throws BusinessException {

		Quotations quotations = new Quotations();

		for (Element element : doc.getElementsByAttributeValue("class", "sodatext")) {
			Quotation quotation = new Quotation();

			for (Element paragraph : element.getElementsByTag("p")) {

				String character = getCharacter(paragraph);
				String quote = paragraph.text();

				if (quote.startsWith("[")) continue;

				quote = StringUtils.substringAfter(quote, character).trim();
				quote = (quote.startsWith(":")) ? StringUtils.substringAfter(quote, ":").trim() : quote;

				quotation.add(QuoteAdapter.transform(character, quote));
			}

			quotations.add(quotation);
		}

		return quotations;
	}

	public Quotations process(String id, String base) throws BusinessException {

		String URL = String.format(URL_TEMPLATE, id);
		SavePageResult savePageResult = new SavePage().process(URL, id, base, "quotes");

		Quotations quotations = parse(savePageResult.getDoc());
		if (quotations.isEmpty()) {
			logger.error("No Results Found (id = %s", id);
			return quotations;
		}

		String results = QuotationsAdapter.toTsv(quotations);
		FileUtils.toFile(results, savePageResult.getOutDat().getAbsolutePath(), Codepage.UTF_8);

		return quotations;
	}
}
