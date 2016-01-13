package org.swtk.data.imdb.json.dto.adapter;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.Quote;

public final class QuoteAdapter {

	public static String toTsv(Collection<Quote> list) {
		StringBuilder sb = new StringBuilder();

		Iterator<Quote> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public static String toTsv(Quote quote) {
		StringBuilder sb = new StringBuilder();

		sb.append(quote.getCharacter());
		sb.append("\t");
		sb.append(quote.getText());

		return sb.toString();
	}

	public static Quote transform(String character, String text) {
		Quote quote = new Quote();

		quote.setCharacter(character);
		quote.setText(text);

		return quote;
	}
}
