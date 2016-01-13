package org.swtk.data.imdb.json.dto.adapter.iterable;

import org.swtk.data.imdb.json.dto.adapter.TaglineAdapter;
import org.swtk.data.imdb.json.dto.iterable.Taglines;

public final class TaglinesAdapter {

	public static String toTsv(Taglines taglines) {
		return TaglineAdapter.toTsv(taglines.getTaglines());
	}
}
