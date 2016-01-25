package org.swtk.data.twitter.core.dto.tdc;

import com.google.gson.annotations.Expose;

public class TdcEntity {

	@Expose
	private String[] hashtags;

	@Expose
	private String[] symbols;

	@Expose
	private String[] urls;

	@Expose
	private String[] user_mentions;

	@Expose
	private boolean favorited;

	@Expose
	private boolean retweeted;

	@Expose
	private String filter_level;

	@Expose
	private String lang;

	public String getFilter_level() {
		return filter_level;
	}

	public String[] getHashtags() {
		return hashtags;
	}

	public String getLang() {
		return lang;
	}

	public String[] getSymbols() {
		return symbols;
	}

	public String[] getUrls() {
		return urls;
	}

	public String[] getUser_mentions() {
		return user_mentions;
	}

	public boolean isFavorited() {
		return favorited;
	}

	public boolean isRetweeted() {
		return retweeted;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public void setFilter_level(String filter_level) {
		this.filter_level = filter_level;
	}

	public void setHashtags(String[] hashtags) {
		this.hashtags = hashtags;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setRetweeted(boolean retweeted) {
		this.retweeted = retweeted;
	}

	public void setSymbols(String[] symbols) {
		this.symbols = symbols;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}

	public void setUser_mentions(String[] user_mentions) {
		this.user_mentions = user_mentions;
	}
}
