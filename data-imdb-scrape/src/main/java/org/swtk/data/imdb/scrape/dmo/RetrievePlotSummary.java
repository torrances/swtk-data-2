package org.swtk.data.imdb.scrape.dmo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.imdb.json.dto.adapter.PlotSummaryAdapter;
import org.swtk.data.imdb.json.dto.adapter.iterable.PlotSummariesAdapter;
import org.swtk.data.imdb.json.dto.iterable.PlotSummaries;
import org.swtk.data.imdb.scrape.dto.SavePageResult;
import org.swtk.data.imdb.scrape.svc.SavePage;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RetrievePlotSummary {

	public static LogManager logger = new LogManager(RetrievePlotSummary.class);

	public static final String URL_TEMPLATE = "http://www.imdb.com/title/%s/plotsummary";

	private PlotSummaries parse(Document doc) throws BusinessException {
		PlotSummaries plotSummaries = new PlotSummaries();

		for (Element element : doc.getElementsByAttributeValue("class", "plotSummary")) {
			String text = StringUtils.trim(element.text());
			plotSummaries.add(PlotSummaryAdapter.transform(text));
		}

		return plotSummaries;
	}

	public PlotSummaries process(String id, String base) throws BusinessException {

		String URL = String.format(URL_TEMPLATE, id);
		SavePageResult savePageResult = new SavePage().process(URL, id, base, "plot-summary");

		PlotSummaries plotSummaries = parse(savePageResult.getDoc());
		if (plotSummaries.isEmpty()) {
			logger.trace("No Results Found (id = %s", id);
			return plotSummaries;
		}

		String results = PlotSummariesAdapter.toTsv(plotSummaries);
		FileUtils.toFile(results, savePageResult.getOutDat().getAbsolutePath(), Codepage.UTF_8);

		return plotSummaries;
	}
}
