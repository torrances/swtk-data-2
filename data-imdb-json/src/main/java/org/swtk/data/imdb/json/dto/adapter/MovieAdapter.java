package org.swtk.data.imdb.json.dto.adapter;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.Movie;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.SetUtils;

public class MovieAdapter {

	public static String toTsv(Collection<Movie> list) {
		StringBuilder sb = new StringBuilder();

		Iterator<Movie> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public static String toTsv(Movie movie) {
		StringBuilder sb = new StringBuilder();

		sb.append(movie.getTitle());
		sb.append("\t");
		sb.append(movie.getDate());
		sb.append("\t");
		sb.append(movie.getDescription());
		sb.append("\t");
		sb.append(movie.getType());
		sb.append("\t");
		sb.append(SetUtils.toString(movie.getGenres(), ","));
		sb.append("\t");
		sb.append(SetUtils.toString(movie.getStars(), ","));
		sb.append("\t");
		sb.append(SetUtils.toString(movie.getDirectors(), ","));
		sb.append("\t");
		sb.append(SetUtils.toString(movie.getWriters(), ","));

		return sb.toString();
	}

	public static Movie transform(String title, String date, String description, String type, Collection<String> genres, Collection<String> stars, Collection<String> directors, Collection<String> writers) throws AdapterValidationException {
		Movie movie = new Movie();

		movie.setTitle(title);
		movie.setDate(date);
		movie.setDescription(description);
		movie.setType(type);
		movie.setGenres(genres);
		movie.setStars(stars);
		movie.setDirectors(directors);
		movie.setWriters(writers);

		return movie;
	}
}
