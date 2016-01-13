package org.swtk.data.imdb.json.dto.adapter.iterable;

import org.swtk.data.imdb.json.dto.adapter.FaqEntryAdapter;
import org.swtk.data.imdb.json.dto.iterable.FaqEntries;

public final class FaqEntriesAdapter {

	public static String toTsv(FaqEntries faqEntries) {
		return FaqEntryAdapter.toTsv(faqEntries.getFaqEntries());
	}
}
