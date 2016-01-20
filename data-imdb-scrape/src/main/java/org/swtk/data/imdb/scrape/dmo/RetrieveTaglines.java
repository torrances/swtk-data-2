package org.swtk.data.imdb.scrape.dmo;

import java.util.Set;
import java.util.TreeSet;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.imdb.json.dto.adapter.TaglineAdapter;
import org.swtk.data.imdb.json.dto.adapter.iterable.TaglinesAdapter;
import org.swtk.data.imdb.json.dto.iterable.Taglines;
import org.swtk.data.imdb.scrape.dto.SavePageResult;
import org.swtk.data.imdb.scrape.svc.SavePage;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RetrieveTaglines {

	public static LogManager logger = new LogManager(RetrieveTaglines.class);

	public static final String URL_TEMPLATE = "http://www.imdb.com/title/%s/taglines";

	private Taglines parse(Document doc) throws BusinessException {

		Taglines taglines = new Taglines();
		for (Element element : doc.getElementsByAttributeValue("id", "taglines_content")) {

			Set<String> set = new TreeSet<String>();
			for (Element e1 : element.getElementsByAttributeValue("class", "soda even"))
				set.add(e1.text());
			for (Element e2 : element.getElementsByAttributeValue("class", "soda odd"))
				set.add(e2.text());

			for (String text : set) {

				text = text.contains("[") ? StringUtils.substringBefore(text, "[").trim() : text;
				if (text.startsWith("It looks like we don't have any Taglines")) continue;

				taglines.add(TaglineAdapter.transform(text));
			}
		}

		return taglines;
	}

	public Taglines process(String id, String base) throws BusinessException {

		String URL = String.format(URL_TEMPLATE, id);
		SavePageResult savePageResult = new SavePage().process(URL, id, base, "taglines");

		Taglines taglines = parse(savePageResult.getDoc());
		if (taglines.isEmpty()) {
			logger.trace("No Results Found (id = %s", id);
			return taglines;
		}

		String results = TaglinesAdapter.toTsv(taglines);
		FileUtils.toFile(results, savePageResult.getOutDat().getAbsolutePath(), Codepage.UTF_8);

		return taglines;
	}
}
