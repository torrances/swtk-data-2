package org.swtk.data.imdb.json.dto.adapter.iterable;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.UserRating;
import org.swtk.data.imdb.json.dto.adapter.UserRatingAdapter;
import org.swtk.data.imdb.json.dto.iterable.UserRatings;

public final class UserRatingsAdapter {

	public static String toTsv(Collection<UserRatings> list) {
		StringBuilder sb = new StringBuilder();
		
		Iterator<UserRatings> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
	
	public static String toTsv(UserRatings userRatings) {
		StringBuilder sb = new StringBuilder();

		Iterator<UserRating> iter = userRatings.iterator();
		while (iter.hasNext()) {
			sb.append(UserRatingAdapter.toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}

		return sb.toString();
	}
}
