package org.swtk.data.imdb.json.dto.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.PlotSummary;
import org.swtk.data.imdb.json.dto.adapter.iterable.PlotSummariesAdapter;

import com.google.gson.annotations.Expose;

public class PlotSummaries implements Iterable<PlotSummary>, Iterator<PlotSummary> {

	@Expose
	private List<PlotSummary> list;
	
	@Ignore
	public void add(PlotSummary... PlotSummaries) {
		for (PlotSummary PlotSummary : PlotSummaries)
			getPlotSummaries().add(PlotSummary);
	}
	
	public List<PlotSummary> getPlotSummaries() {
		if (null == list) setPlotSummaries(new ArrayList<PlotSummary>());
		return list;
	}

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public boolean hasNext() {
		return iterator().hasNext();
	}

	@Ignore
	public boolean isEmpty() {
		return !iterator().hasNext();
	}

	@Override
	@Ignore
	public Iterator<PlotSummary> iterator() {
		return getPlotSummaries().iterator();
	}

	@Override
	public PlotSummary next() {
		return iterator().next();
	}

	private void setPlotSummaries(List<PlotSummary> list) {
		this.list = list;
	}

	@Ignore
	public int size() {
		return getPlotSummaries().size();
	}

	@Override
	@Ignore
	public String toString() {
		return PlotSummariesAdapter.toTsv(this);
	}
}
