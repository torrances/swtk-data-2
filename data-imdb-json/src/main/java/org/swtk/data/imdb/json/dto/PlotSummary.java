package org.swtk.data.imdb.json.dto;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.adapter.PlotSummaryAdapter;

import com.google.gson.annotations.Expose;

public class PlotSummary {

	@Expose
	private String text;

	public String getText() {
		return text;
	}

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	@Ignore
	public String toString() {
		return PlotSummaryAdapter.toTsv(this);
	}
}
