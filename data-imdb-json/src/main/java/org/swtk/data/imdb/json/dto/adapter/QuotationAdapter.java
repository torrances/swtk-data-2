package org.swtk.data.imdb.json.dto.adapter;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.Quotation;
import org.swtk.data.imdb.json.dto.Quote;

public final class QuotationAdapter {

	public static String toTsv(Collection<Quotation> list) {
		StringBuilder sb = new StringBuilder();

		Iterator<Quotation> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}

		return sb.toString();
	}
	
	public static String toTsv(Quotation quotes) {
		StringBuilder sb = new StringBuilder();

		Iterator<Quote> iter = quotes.iterator();
		while (iter.hasNext()) {
			sb.append(QuoteAdapter.toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}

		sb.append(System.lineSeparator());
		return sb.toString();
	}
}
