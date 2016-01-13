package org.swtk.data.imdb.json.dto;

import org.swtk.data.imdb.json.dto.iterable.CastMembers;
import org.swtk.data.imdb.json.dto.iterable.FaqEntries;
import org.swtk.data.imdb.json.dto.iterable.Keywords;
import org.swtk.data.imdb.json.dto.iterable.Movies;
import org.swtk.data.imdb.json.dto.iterable.PlotSummaries;
import org.swtk.data.imdb.json.dto.iterable.Quotations;
import org.swtk.data.imdb.json.dto.iterable.Synopses;
import org.swtk.data.imdb.json.dto.iterable.Taglines;
import org.swtk.data.imdb.json.dto.iterable.UserRatings;

import com.google.gson.annotations.Expose;

public class ImdbTitle {

	@Expose
	private CastMembers castMembers;

	@Expose
	private FaqEntries faqEntries;

	@Expose
	private String id;

	@Expose
	private Keywords keywords;

	@Expose
	private Movies movies;

	@Expose
	private PlotSummaries plotSummaries;

	@Expose
	private Quotations quotations;

	@Expose
	private Synopses synopses;

	@Expose
	private Taglines taglines;

	@Expose
	private UserRatings userRatings;

	public CastMembers getCastMembers() {
		return castMembers;
	}

	public FaqEntries getFaqEntries() {
		return faqEntries;
	}

	public String getId() {
		return id;
	}

	public Keywords getKeywords() {
		return keywords;
	}

	public Movies getMovies() {
		return movies;
	}

	public PlotSummaries getPlotSummaries() {
		return plotSummaries;
	}

	public Quotations getQuotations() {
		return quotations;
	}

	public Synopses getSynopses() {
		return synopses;
	}

	public Taglines getTaglines() {
		return taglines;
	}

	public UserRatings getUserRatings() {
		return userRatings;
	}

	public void setCastMembers(CastMembers castMembers) {
		this.castMembers = castMembers;
	}

	public void setFaqEntries(FaqEntries faqEntries) {
		this.faqEntries = faqEntries;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}

	public void setPlotSummaries(PlotSummaries plotSummaries) {
		this.plotSummaries = plotSummaries;
	}

	public void setQuotations(Quotations quotations) {
		this.quotations = quotations;
	}

	public void setSynopses(Synopses synopses) {
		this.synopses = synopses;
	}

	public void setTaglines(Taglines taglines) {
		this.taglines = taglines;
	}

	public void setUserRatings(UserRatings userRatings) {
		this.userRatings = userRatings;
	}
}
