package org.swtk.data.imdb.json.dto.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.Tagline;
import org.swtk.data.imdb.json.dto.adapter.iterable.TaglinesAdapter;

import com.google.gson.annotations.Expose;

public class Taglines implements Iterable<Tagline>, Iterator<Tagline> {

	@Expose
	private List<Tagline> list;
	
	@Ignore
	public void add(Tagline... Taglines) {
		for (Tagline Tagline : Taglines)
			getTaglines().add(Tagline);
	}
	
	public List<Tagline> getTaglines() {
		if (null == list) setTaglines(new ArrayList<Tagline>());
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
	public Iterator<Tagline> iterator() {
		return getTaglines().iterator();
	}

	@Override
	public Tagline next() {
		return iterator().next();
	}

	private void setTaglines(List<Tagline> list) {
		this.list = list;
	}

	@Ignore
	public int size() {
		return getTaglines().size();
	}

	@Override
	@Ignore
	public String toString() {
		return TaglinesAdapter.toTsv(this);
	}
}
