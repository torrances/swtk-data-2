package org.swtk.data.imdb.scrape.dmo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.imdb.json.dto.adapter.SynopsisAdapter;
import org.swtk.data.imdb.json.dto.adapter.iterable.SynopsesAdapter;
import org.swtk.data.imdb.json.dto.iterable.Synopses;
import org.swtk.data.imdb.scrape.dto.SavePageResult;
import org.swtk.data.imdb.scrape.svc.SavePage;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RetrieveSynoposes {

	public static LogManager logger = new LogManager(RetrieveSynoposes.class);

	public static final String URL_TEMPLATE = "http://www.imdb.com/title/%s/synopsis";
	
	private Synopses parse(Document doc) throws BusinessException {
		Synopses synopses = new Synopses();

		for (Element element : doc.getElementsByAttributeValue("id", "swiki.2.1")) {
			String text = StringUtils.trim(element.text());
			if (!StringUtils.hasValue(text)) continue;
			synopses.add(SynopsisAdapter.transform(text));
		}

		return synopses;
	}

	public Synopses process(String id, String base) throws BusinessException {

		String URL = String.format(URL_TEMPLATE, id);
		SavePageResult savePageResult = new SavePage().process(URL, id, base, "synopsis");

		Synopses synopses = parse(savePageResult.getDoc());
		if (synopses.isEmpty()) {
			logger.trace("No Results Found (id = %s", id);
			return synopses;
		}

		String results = SynopsesAdapter.toTsv(synopses);
		FileUtils.toFile(results, savePageResult.getOutDat().getAbsolutePath(), Codepage.UTF_8);

		return synopses;
	}
}
