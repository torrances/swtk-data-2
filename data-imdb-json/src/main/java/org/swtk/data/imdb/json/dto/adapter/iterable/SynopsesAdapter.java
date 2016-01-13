package org.swtk.data.imdb.json.dto.adapter.iterable;

import org.swtk.data.imdb.json.dto.adapter.SynopsisAdapter;
import org.swtk.data.imdb.json.dto.iterable.Synopses;

public final class SynopsesAdapter {

	public static String toTsv(Synopses synopses) {
		return SynopsisAdapter.toTsv(synopses.getSynopses());
	}
}
