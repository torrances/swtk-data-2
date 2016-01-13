package org.swtk.data.imdb.json.dto.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.Movie;
import org.swtk.data.imdb.json.dto.adapter.iterable.MoviesAdapter;

import com.google.gson.annotations.Expose;

public class Movies implements Iterable<Movie>, Iterator<Movie> {

	@Expose
	private List<Movie> list;
	
	@Ignore
	public void add(Movie... Movies) {
		for (Movie Movie : Movies)
			getMovies().add(Movie);
	}
	
	public List<Movie> getMovies() {
		if (null == list) setMovies(new ArrayList<Movie>());
		return list;
	}

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public boolean hasNext() {
		return iterator().hasNext();
	}

	@Ignore
	public boolean isEmpty() {
		return !iterator().hasNext();
	}

	@Override
	@Ignore
	public Iterator<Movie> iterator() {
		return getMovies().iterator();
	}

	@Override
	public Movie next() {
		return iterator().next();
	}

	private void setMovies(List<Movie> list) {
		this.list = list;
	}

	@Ignore
	public int size() {
		return getMovies().size();
	}

	@Override
	@Ignore
	public String toString() {
		return MoviesAdapter.toTsv(this);
	}
}
