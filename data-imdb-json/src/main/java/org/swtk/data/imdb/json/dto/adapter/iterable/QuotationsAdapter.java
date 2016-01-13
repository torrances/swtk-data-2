package org.swtk.data.imdb.json.dto.adapter.iterable;

import org.swtk.data.imdb.json.dto.adapter.QuotationAdapter;
import org.swtk.data.imdb.json.dto.iterable.Quotations;

public final class QuotationsAdapter {

	public static String toTsv(Quotations quotations) {
		return QuotationAdapter.toTsv(quotations.getQuotations());
	}
}
