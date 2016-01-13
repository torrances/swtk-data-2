package org.swtk.data.imdb.json.dto.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.Synopsis;
import org.swtk.data.imdb.json.dto.adapter.iterable.SynopsesAdapter;

import com.google.gson.annotations.Expose;

public class Synopses implements Iterable<Synopsis>, Iterator<Synopsis> {

	@Expose
	private List<Synopsis> list;
	
	@Ignore
	public void add(Synopsis... synopses) {
		for (Synopsis synopsis : synopses)
			getSynopses().add(synopsis);
	}
	
	public List<Synopsis> getSynopses() {
		if (null == list) setSynopses(new ArrayList<Synopsis>());
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
	public Iterator<Synopsis> iterator() {
		return getSynopses().iterator();
	}

	@Override
	public Synopsis next() {
		return iterator().next();
	}

	private void setSynopses(List<Synopsis> list) {
		this.list = list;
	}

	@Ignore
	public int size() {
		return getSynopses().size();
	}

	@Override
	@Ignore
	public String toString() {
		return SynopsesAdapter.toTsv(this);
	}
}
