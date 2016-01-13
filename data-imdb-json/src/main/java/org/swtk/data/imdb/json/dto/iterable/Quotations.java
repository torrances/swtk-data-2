package org.swtk.data.imdb.json.dto.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.Quotation;
import org.swtk.data.imdb.json.dto.adapter.iterable.QuotationsAdapter;

import com.google.gson.annotations.Expose;

public class Quotations implements Iterable<Quotation>, Iterator<Quotation> {

	@Expose
	private List<Quotation> list;
	
	@Ignore
	public void add(Quotation... Quotations) {
		for (Quotation Quotation : Quotations)
			getQuotations().add(Quotation);
	}
	
	public List<Quotation> getQuotations() {
		if (null == list) setQuotations(new ArrayList<Quotation>());
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
	public Iterator<Quotation> iterator() {
		return getQuotations().iterator();
	}

	@Override
	public Quotation next() {
		return iterator().next();
	}

	private void setQuotations(List<Quotation> list) {
		this.list = list;
	}

	@Ignore
	public int size() {
		return getQuotations().size();
	}

	@Override
	@Ignore
	public String toString() {
		return QuotationsAdapter.toTsv(this);
	}
}
