package org.swtk.data.imdb.json.dto.adapter;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.UserRating;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class UserRatingAdapter {

	public static String toTsv(Collection<UserRating> list) {
		StringBuilder sb = new StringBuilder();

		Iterator<UserRating> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext())
				sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public static String toTsv(UserRating userRating) {
		StringBuilder sb = new StringBuilder();

		sb.append(userRating.getType());
		sb.append("\t");
		sb.append(userRating.getTotal());
		sb.append("\t");
		sb.append(userRating.getAverage());

		return sb.toString();
	}

	public static UserRating transform(String type, String total, String average) throws AdapterValidationException {
		UserRating userRating = new UserRating();

		userRating.setType(type);
		userRating.setTotal(total);
		userRating.setAverage(average);

		return userRating;
	}
}
