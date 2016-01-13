package org.swtk.data.imdb.json.dto.adapter;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.PlotSummary;

public final class PlotSummaryAdapter {

	public static String toTsv(Collection<PlotSummary> list) {
		StringBuilder sb = new StringBuilder();

		Iterator<PlotSummary> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public static String toTsv(PlotSummary plotSummary) {
		StringBuilder sb = new StringBuilder();

		sb.append(plotSummary.getText());

		return sb.toString();
	}

	public static PlotSummary transform(String text) {
		PlotSummary plotSummary = new PlotSummary();

		plotSummary.setText(text);

		return plotSummary;
	}
}
