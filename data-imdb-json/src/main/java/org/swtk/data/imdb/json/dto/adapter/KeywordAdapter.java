package org.swtk.data.imdb.json.dto.adapter;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.Keyword;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class KeywordAdapter {

	public static String toTsv(Collection<Keyword> list) {
		StringBuilder sb = new StringBuilder();

		Iterator<Keyword> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public static String toTsv(Keyword keyword) {
		StringBuilder sb = new StringBuilder();

		sb.append(keyword.getText());
		sb.append("\t");
		sb.append(keyword.getPositiveVotes());
		sb.append("\t");
		sb.append(keyword.getTotalVotes());

		return sb.toString();
	}

	public static Keyword transform(String text, int positiveVotes, int totalVotes) throws AdapterValidationException {
		Keyword keyword = new Keyword();

		keyword.setText(text);
		keyword.setPositiveVotes(positiveVotes);
		keyword.setTotalVotes(totalVotes);

		return keyword;
	}
}
