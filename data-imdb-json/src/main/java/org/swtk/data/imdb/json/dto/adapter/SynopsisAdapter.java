package org.swtk.data.imdb.json.dto.adapter;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.Synopsis;

public final class SynopsisAdapter {

	public static String toTsv(Collection<Synopsis> list) {
		StringBuilder sb = new StringBuilder();

		Iterator<Synopsis> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}

		return sb.toString();
	}
	
	public static String toTsv(Synopsis synopsis) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(synopsis.getText());
		
		return sb.toString();
	}

	public static Synopsis transform(String text) {
		Synopsis synopsis = new Synopsis();

		synopsis.setText(text);

		return synopsis;
	}
}
