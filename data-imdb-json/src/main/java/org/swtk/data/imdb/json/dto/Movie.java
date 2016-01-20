package org.swtk.data.imdb.json.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.adapter.MovieAdapter;

import com.google.gson.annotations.Expose;

public class Movie {

	@Expose
	private String date;

	@Expose
	private String description;

	@Expose
	private Collection<String> directors;

	@Expose
	private Collection<String> genres;

	@Expose
	private Collection<String> stars;

	@Expose
	private String title;

	@Expose
	private String type;

	@Expose
	private Collection<String> writers;

	@Ignore
	public void addDirectors(String... directors) {
		for (String director : directors)
			getDirectors().add(director);
	}

	@Ignore
	public void addGenres(String... genres) {
		for (String genre : genres)
			getGenres().add(genre);
	}

	@Ignore
	public void addStars(String... stars) {
		for (String star : stars)
			getStars().add(star);
	}

	@Ignore
	public void addWriters(String... writers) {
		for (String writer : writers)
			getWriters().add(writer);
	}

	public String getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public Collection<String> getDirectors() {
		if (null == directors) setDirectors(new ArrayList<String>());
		return directors;
	}

	public Collection<String> getGenres() {
		if (null == genres) setGenres(new ArrayList<String>());
		return genres;
	}

	public Collection<String> getStars() {
		if (null == stars) setStars(new ArrayList<String>());
		return stars;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public Collection<String> getWriters() {
		if (null == writers) setWriters(new ArrayList<String>());
		return writers;
	}

	public boolean hasDescription() {
		return null != getDescription() && !getDescription().isEmpty();
	}

	public boolean hasGenres() {
		return null != getGenres() && !getGenres().isEmpty();
	}

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDirectors(Collection<String> directors) {
		this.directors = directors;
	}

	public void setGenres(Collection<String> genres) {
		this.genres = genres;
	}

	public void setStars(Collection<String> stars) {
		this.stars = stars;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setWriters(Collection<String> writers) {
		this.writers = writers;
	}

	@Override
	@Ignore
	public String toString() {
		return MovieAdapter.toTsv(this);
	}
}
