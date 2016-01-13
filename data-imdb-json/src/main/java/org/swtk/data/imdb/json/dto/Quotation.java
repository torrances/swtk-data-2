package org.swtk.data.imdb.json.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.adapter.QuotationAdapter;

import com.google.gson.annotations.Expose;

public class Quotation implements Iterable<Quote>, Iterator<Quote> {

	@Expose
	private List<Quote> quotes;
	
	@Ignore
	public void add(Quote... Quotes) {
		for (Quote Quote : Quotes)
			getQuotes().add(Quote);
	}
	
	public List<Quote> getQuotes() {
		if (null == quotes) setQuotes(new ArrayList<Quote>());
		return quotes;
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
	public Iterator<Quote> iterator() {
		return getQuotes().iterator();
	}

	@Override
	public Quote next() {
		return iterator().next();
	}

	private void setQuotes(List<Quote> list) {
		this.quotes = list;
	}

	@Ignore
	public int size() {
		return getQuotes().size();
	}

	@Override
	@Ignore
	public String toString() {
		return QuotationAdapter.toTsv(this);
	}
}
