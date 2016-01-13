package org.swtk.data.imdb.json.dto.adapter.iterable;

import org.swtk.data.imdb.json.dto.adapter.MovieAdapter;
import org.swtk.data.imdb.json.dto.iterable.Movies;

public final class MoviesAdapter {

	public static String toTsv(Movies movies) {
		return MovieAdapter.toTsv(movies.getMovies());
	}
}
