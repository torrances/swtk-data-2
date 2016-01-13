package org.swtk.data.imdb.json.dto.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.Keyword;
import org.swtk.data.imdb.json.dto.adapter.iterable.KeywordsAdapter;

import com.google.gson.annotations.Expose;

public class Keywords implements Iterable<Keyword>, Iterator<Keyword> {

	@Expose
	private List<Keyword> list;
	
	@Ignore
	public void add(Keyword... Keywords) {
		for (Keyword Keyword : Keywords)
			getKeywords().add(Keyword);
	}
	
	public List<Keyword> getKeywords() {
		if (null == list) setKeywords(new ArrayList<Keyword>());
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
	public Iterator<Keyword> iterator() {
		return getKeywords().iterator();
	}

	@Override
	public Keyword next() {
		return iterator().next();
	}

	private void setKeywords(List<Keyword> list) {
		this.list = list;
	}

	@Ignore
	public int size() {
		return getKeywords().size();
	}

	@Override
	@Ignore
	public String toString() {
		return KeywordsAdapter.toTsv(this);
	}
}
