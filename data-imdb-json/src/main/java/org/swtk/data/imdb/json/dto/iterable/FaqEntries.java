package org.swtk.data.imdb.json.dto.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.FaqEntry;
import org.swtk.data.imdb.json.dto.adapter.iterable.FaqEntriesAdapter;

import com.google.gson.annotations.Expose;

public class FaqEntries implements Iterable<FaqEntry>, Iterator<FaqEntry> {

	@Expose
	private List<FaqEntry> list;
	
	@Ignore
	public void add(FaqEntry... FaqEntries) {
		for (FaqEntry FaqEntry : FaqEntries)
			getFaqEntries().add(FaqEntry);
	}
	
	public List<FaqEntry> getFaqEntries() {
		if (null == list) setFaqEntries(new ArrayList<FaqEntry>());
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
	public Iterator<FaqEntry> iterator() {
		return getFaqEntries().iterator();
	}

	@Override
	public FaqEntry next() {
		return iterator().next();
	}

	private void setFaqEntries(List<FaqEntry> list) {
		this.list = list;
	}

	@Ignore
	public int size() {
		return getFaqEntries().size();
	}

	@Override
	@Ignore
	public String toString() {
		return FaqEntriesAdapter.toTsv(this);
	}
}
