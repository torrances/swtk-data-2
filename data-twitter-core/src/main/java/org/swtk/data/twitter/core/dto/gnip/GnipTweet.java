package org.swtk.data.twitter.core.dto.gnip;

import com.google.gson.annotations.Expose;

public class GnipTweet {
	
	@Expose
	private GnipActor actor;

	@Expose
	private String id;

	@Expose
	private String objectType;

	@Expose
	private String verb;

	@Expose
	private String postedTime;

	@Expose
	private String link;

	@Expose
	private String body;

	@Expose
	private Integer favoritesCount;

	@Expose
	private String twitter_filter_level;

	@Expose
	private String twitter_lang;

	@Expose
	private Integer retweetCount;

	public GnipActor getActor() {
		return actor;
	}

	public String getBody() {
		return body;
	}

	public Integer getFavoritesCount() {
		return favoritesCount;
	}

	public String getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	public String getObjectType() {
		return objectType;
	}

	public String getPostedTime() {
		return postedTime;
	}

	public Integer getRetweetCount() {
		return retweetCount;
	}

	public String getTwitter_filter_level() {
		return twitter_filter_level;
	}

	public String getTwitter_lang() {
		return twitter_lang;
	}

	public String getVerb() {
		return verb;
	}

	public void setActor(GnipActor actor) {
		this.actor = actor;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public void setFavoritesCount(Integer favoritesCount) {
		this.favoritesCount = favoritesCount;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}
	
	public void setRetweetCount(Integer retweetCount) {
		this.retweetCount = retweetCount;
	}
	
	public void setTwitter_filter_level(String twitter_filter_level) {
		this.twitter_filter_level = twitter_filter_level;
	}
	
	public void setTwitter_lang(String twitter_lang) {
		this.twitter_lang = twitter_lang;
	}
	
	public void setVerb(String verb) {
		this.verb = verb;
	}
}
