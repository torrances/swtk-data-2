package org.swtk.data.imdb.json.dto.adapter.iterable;

import org.swtk.data.imdb.json.dto.adapter.CastMemberAdapter;
import org.swtk.data.imdb.json.dto.iterable.CastMembers;

public final class CastMembersAdapter {

	public static String toTsv(CastMembers castMembers) {
		return CastMemberAdapter.toTsv(castMembers.getCastMembers());
	}
}
