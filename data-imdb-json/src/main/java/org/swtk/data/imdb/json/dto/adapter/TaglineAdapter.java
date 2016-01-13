package org.swtk.data.imdb.json.dto.adapter;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.Tagline;

public final class TaglineAdapter {

	public static String toTsv(Collection<Tagline> list) {
		StringBuilder sb = new StringBuilder();

		Iterator<Tagline> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public static String toTsv(Tagline tagline) {
		StringBuilder sb = new StringBuilder();

		sb.append(tagline.getText());

		return sb.toString();
	}

	public static Tagline transform(String text) {
		Tagline tagline = new Tagline();

		tagline.setText(text);

		return tagline;
	}
}
