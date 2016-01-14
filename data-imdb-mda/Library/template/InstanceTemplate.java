package org.swtk.dict.imdb.dmo.#pkgSuffix;															#EOL

import org.swtk.data.imdb.json.dto.ImdbTitle;
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
import com.trimc.blogger.commons.utils.GsonUtils;
																									#EOL
public class #className {																			#EOL
																									#EOL
	@Expose																							#EOL
	public static ImdbTitle entry;																	#EOL
																									#EOL
	@Expose																							#EOL
	public static String generated;																	#EOL
																									#EOL
	static {																						#EOL
		entry.setCastMembers(GsonUtils.toObject("#castMembers", CastMembers.class));				#EOL
		entry.setFaqEntries(GsonUtils.toObject("#faqEntries", FaqEntries.class));					#EOL
		entry.setId("#id");																			#EOL
		entry.setKeywords(GsonUtils.toObject("#keywords", Keywords.class));							#EOL
		entry.setMovies(GsonUtils.toObject("#movies", Movies.class));								#EOL
		entry.setPlotSummaries(GsonUtils.toObject("#plotSummaries", PlotSummaries.class));			#EOL
		entry.setQuotations(GsonUtils.toObject("#quotations", Quotations.class));					#EOL
		entry.setSynopses(GsonUtils.toObject("#synopses", Synopses.class));							#EOL
		entry.setTaglines(GsonUtils.toObject("#tagLines", Taglines.class));							#EOL
		entry.setUserRatings(GsonUtils.toObject("#userRatings", UserRatings.class));				#EOL
	}																								#EOL
}																									#EOL
																									#EOL
