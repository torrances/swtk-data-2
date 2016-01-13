package org.swtk.data.imdb.scrape.svc;

import org.swtk.data.imdb.json.dmo.ImdbJsonSerializer;
import org.swtk.data.imdb.json.dto.ImdbTitle;
import org.swtk.data.imdb.json.dto.RunnerContract;
import org.swtk.data.imdb.scrape.dmo.RetrieveCastMembers;
import org.swtk.data.imdb.scrape.dmo.RetrieveFaqEntries;
import org.swtk.data.imdb.scrape.dmo.RetrieveKeywords;
import org.swtk.data.imdb.scrape.dmo.RetrieveMovie;
import org.swtk.data.imdb.scrape.dmo.RetrievePlotSummary;
import org.swtk.data.imdb.scrape.dmo.RetrieveQuotes;
import org.swtk.data.imdb.scrape.dmo.RetrieveSynoposes;
import org.swtk.data.imdb.scrape.dmo.RetrieveTaglines;
import org.swtk.data.imdb.scrape.dmo.RetrieveUserRatings;

import com.trimc.blogger.commons.LogManager;

public class ScrapeMovie {

	public static LogManager logger = new LogManager(ScrapeMovie.class);

	public String process(final String ID, RunnerContract contract) throws Exception {

		ImdbTitle imdbTitle = new ImdbTitle();
		imdbTitle.setId(ID);

		try {
			imdbTitle.setCastMembers(new RetrieveCastMembers().process(ID, contract.getBaseDirectory()));
		} catch (Exception e) {
			logger.error("Exception (id = %s, type = %s, message = %s", ID, "cast", e.getMessage());
		}

		try {
			imdbTitle.setFaqEntries(new RetrieveFaqEntries().process(ID, contract.getBaseDirectory()));
		} catch (Exception e) {
			logger.error("Exception (id = %s, type = %s, message = %s", ID, "faq", e.getMessage());
		}

		try {
			imdbTitle.setPlotSummaries(new RetrievePlotSummary().process(ID, contract.getBaseDirectory()));
		} catch (Exception e) {
			logger.error("Exception (id = %s, type = %s, message = %s", ID, "plot", e.getMessage());
		}

		try {
			imdbTitle.setKeywords(new RetrieveKeywords().process(ID, contract.getBaseDirectory()));
		} catch (Exception e) {
			logger.error("Exception (id = %s, type = %s, message = %s", ID, "keywords", e.getMessage());
		}

		try {
			imdbTitle.setTaglines(new RetrieveTaglines().process(ID, contract.getBaseDirectory()));
		} catch (Exception e) {
			logger.error("Exception (id = %s, type = %s, message = %s", ID, "tagline", e.getMessage());
		}

		try {
			imdbTitle.setSynopses(new RetrieveSynoposes().process(ID, contract.getBaseDirectory()));
		} catch (Exception e) {
			logger.error("Exception (id = %s, type = %s, message = %s", ID, "synopsis", e.getMessage());
		}

		try {
			imdbTitle.setQuotations(new RetrieveQuotes().process(ID, contract.getBaseDirectory()));
		} catch (Exception e) {
			logger.error("Exception (id = %s, type = %s, message = %s", ID, "quotes", e.getMessage());
		}

		try {
			imdbTitle.setMovies(new RetrieveMovie().process(ID, contract.getBaseDirectory()));
		} catch (Exception e) {
			logger.error("Exception (id = %s, type = %s, message = %s", ID, "movie", e.getMessage());
		}

		try {
			imdbTitle.setUserRatings(new RetrieveUserRatings().process(ID, contract.getBaseDirectory()));
		} catch (Exception e) {
			logger.error("Exception (id = %s, type = %s, message = %s", ID, "ratings", e.getMessage());
		}

		return ImdbJsonSerializer.toPrettyString(imdbTitle);
	}
}
