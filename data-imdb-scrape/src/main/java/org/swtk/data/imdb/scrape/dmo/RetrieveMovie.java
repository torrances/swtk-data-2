package org.swtk.data.imdb.scrape.dmo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.imdb.json.dto.Movie;
import org.swtk.data.imdb.json.dto.adapter.MovieAdapter;
import org.swtk.data.imdb.json.dto.adapter.iterable.MoviesAdapter;
import org.swtk.data.imdb.json.dto.iterable.Movies;
import org.swtk.data.imdb.scrape.dto.SavePageResult;
import org.swtk.data.imdb.scrape.svc.SavePage;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RetrieveMovie {

	public static LogManager logger = new LogManager(RetrieveMovie.class);

	private Collection<String> getGenres(Document doc) {
		List<String> list = new ArrayList<String>();

		Element genreElement = doc.getElementsByAttributeValueContaining("class", "rec-cert-genre").first();
		for (String value : StringUtils.split(genreElement.text(), "|"))
			list.add(value.trim());

		return list;
	}

	private Collection<String> getStars(Document doc) {
		List<String> list = new ArrayList<String>();

		for (Element actorsElement : doc.getElementsByAttributeValue("itemprop", "actors"))
			for (Element actorElement : actorsElement.getElementsByAttributeValue("itemprop", "name"))
				list.add(actorElement.text().trim());

		return list;
	}

	private Collection<String> getDirectors(Document doc) {
		List<String> list = new ArrayList<String>();

		for (Element actorsElement : doc.getElementsByAttributeValue("itemprop", "director"))
			for (Element actorElement : actorsElement.getElementsByAttributeValue("itemprop", "name"))
				list.add(actorElement.text().trim());

		return list;
	}

	private Collection<String> getWriters(Document doc) {
		List<String> list = new ArrayList<String>();

		for (Element actorsElement : doc.getElementsByAttributeValue("itemprop", "creator"))
			for (Element actorElement : actorsElement.getElementsByAttributeValue("itemprop", "name"))
				list.add(actorElement.text().trim());

		return list;
	}

	public static final String URL_TEMPLATE = "http://www.imdb.com/title/%s";

	private Movies parse(Document doc) throws BusinessException {
		Movies movies = new Movies();

		Element titleElement = doc.getElementsByAttributeValue("property", "og:title").first();
		String title = titleElement.attr("content");

		Element descriptionElement = doc.getElementsByAttributeValue("property", "og:description").first();
		String description = descriptionElement.attr("content");

		Element typeElement = doc.getElementsByAttributeValue("property", "og:type").first();
		String type = typeElement.attr("content");

		Element dateElement = doc.getElementsByAttributeValue("itemprop", "datePublished").first();
		String date = dateElement.attr("content");

		Movie movie = MovieAdapter.transform(title, date, description, type, getGenres(doc), getStars(doc), getDirectors(doc), getWriters(doc));
		movies.add(movie);

		return movies;
	}

	public Movies process(String id, String base) throws BusinessException {

		String URL = String.format(URL_TEMPLATE, id);
		SavePageResult savePageResult = new SavePage().process(URL, id, base, "movie");

		Movies movies = parse(savePageResult.getDoc());
		if (movies.isEmpty()) {
			logger.error("No Results Found (id = %s", id);
			return movies;
		}

		String results = MoviesAdapter.toTsv(movies);
		FileUtils.toFile(results, savePageResult.getOutDat().getAbsolutePath(), Codepage.UTF_8);

		return movies;
	}
}
