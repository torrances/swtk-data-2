package org.swtk.data.imdb.json.dto.adapter.iterable;

import org.swtk.data.imdb.json.dto.adapter.KeywordAdapter;
import org.swtk.data.imdb.json.dto.iterable.Keywords;

public final class KeywordsAdapter {

	public static String toTsv(Keywords keywords) {
		return KeywordAdapter.toTsv(keywords.getKeywords());
	}
}
