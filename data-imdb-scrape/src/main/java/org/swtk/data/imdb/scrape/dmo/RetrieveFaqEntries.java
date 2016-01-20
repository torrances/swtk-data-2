package org.swtk.data.imdb.scrape.dmo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.imdb.json.dto.adapter.FaqEntryAdapter;
import org.swtk.data.imdb.json.dto.adapter.iterable.FaqEntriesAdapter;
import org.swtk.data.imdb.json.dto.iterable.FaqEntries;
import org.swtk.data.imdb.scrape.dto.SavePageResult;
import org.swtk.data.imdb.scrape.svc.SavePage;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RetrieveFaqEntries {

	public static LogManager logger = new LogManager(RetrieveFaqEntries.class);

	public static final String URL_TEMPLATE = "http://www.imdb.com/title/%s/faq";

	private String getAnswer(Element swikiElement, String swikiId) {
		for (Element paragraph : swikiElement.getElementsByTag("p")) {

			String paragraphId = paragraph.attr("id");
			if (!paragraphId.startsWith(swikiId)) continue;

			logger.trace("\t\t\tLocated Element (tag = 'paragraph', text = %s)", paragraph.text());
			return paragraph.text();
		}

		return null;
	}

	private String getQuestion(Element swikiElement, String swikiId) {
		for (Element span : swikiElement.getElementsByTag("span")) {

			String spanId = span.attr("id");
			if (!spanId.startsWith(swikiId)) continue;

			logger.trace("\t\t\tLocated Element (tag = 'span', text = %s)", span.text());
			return span.text();
		}

		return null;
	}

	private FaqEntries parse(Document doc) throws BusinessException {
		FaqEntries faqEntries = new FaqEntries();

		for (Element element : doc.getElementsByAttributeValue("id", "swiki_faq_toc")) {
			logger.trace("Located Element (id = 'swiki_faq_toc')");

			for (Element listItem : element.getElementsByTag("li")) {
				logger.trace("\tLocated Element (tag = 'li')");

				for (Element aHref : listItem.getElementsByTag("a")) {
					logger.trace("\t\tLocated Element (tag = 'a')");

					String id = aHref.attr("href");
					String swikiId = "swiki" + StringUtils.substringAfter(id, "#");
					logger.trace("\t\tLocated Element (tag = 'a', swiki-id = %s)", swikiId);

					for (Element swikiElement : doc.getElementsByAttributeValue("id", swikiId)) {
						logger.trace("\t\tLocated Corresponding Swiki Element");

						String question = getQuestion(swikiElement, swikiId);
						String answer = getAnswer(swikiElement, swikiId);

						faqEntries.add(FaqEntryAdapter.transform(id, question, answer));
					}
				}
			}
		}

		return faqEntries;
	}

	public FaqEntries process(String id, String base) throws BusinessException {

		String URL = String.format(URL_TEMPLATE, id);
		SavePageResult savePageResult = new SavePage().process(URL, id, base, "faq");

		FaqEntries faqEntries = parse(savePageResult.getDoc());
		if (faqEntries.isEmpty()) {
			logger.trace("No Results Found (id = %s", id);
			return faqEntries;
		}

		String results = FaqEntriesAdapter.toTsv(faqEntries);
		FileUtils.toFile(results, savePageResult.getOutDat().getAbsolutePath(), Codepage.UTF_8);

		return faqEntries;
	}
}
