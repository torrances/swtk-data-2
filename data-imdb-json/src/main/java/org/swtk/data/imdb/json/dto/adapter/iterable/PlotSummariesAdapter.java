package org.swtk.data.imdb.json.dto.adapter.iterable;

import org.swtk.data.imdb.json.dto.adapter.PlotSummaryAdapter;
import org.swtk.data.imdb.json.dto.iterable.PlotSummaries;

public final class PlotSummariesAdapter {

	public static String toTsv(PlotSummaries plotSummaries) {
		return PlotSummaryAdapter.toTsv(plotSummaries.getPlotSummaries());
	}
}
