package org.swtk.data.twitter.core.dto.generic;

import java.util.Set;
import java.util.TreeSet;

import com.google.gson.annotations.Expose;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class Tweet {

	@Expose
	private String user;

	/**
	 * 	Purpose:
	 * 	Raw Text
	 */
	@Expose
	private String text;

	/**
	 * 	Purpose:
	 * 	Normalized:
	 * 	1. 	all lowercase
	 * 	2.	no punctuation
	 */
	@Expose
	private String normalized;
	
	/**
	 * 	Purpose:
	 * 	Same as "normalized" with:
	 * 	1. 	all hashtags removed
	 * 	2.	all URLs removed
	 */
	@Expose
	private String normalizedNoHashtagsOrURLs;

	/**
	 * 	Purpose:
	 * 	Hashtags that are
	 * 	1. extracted from the tweet
	 */
	@Expose
	private Set<String> hashtags;

	public Set<String> getHashtags() {
		if (null == hashtags) setHashtags(new TreeSet<String>());
		return hashtags;
	}

	public String getNormalized() {
		return normalized;
	}

	public String getNormalizedNoHashtagsOrURLs() {
		return normalizedNoHashtagsOrURLs;
	}
	
	public String getText() {
		return text;
	}

	public String getUser() {
		return user;
	}

	public boolean hasNormalized() {
		return StringUtils.hasValue(getNormalized());
	}

	public boolean hasNormalizedNoHashtagsOrURLs() {
		return StringUtils.hasValue(getNormalizedNoHashtagsOrURLs());
	}

	public boolean hasText() {
		return StringUtils.hasValue(getText());
	}

	public boolean hasUser() {
		return null != getUser();
	}

	public void setHashtags(Set<String> hashtags) {
		this.hashtags = hashtags;
	}

	public void setNormalized(String normalized) {
		this.normalized = normalized;
	}

	public void setNormalizedNoHashtagsOrURLs(String normalizedNoHashtagsOrURLs) {
		this.normalizedNoHashtagsOrURLs = normalizedNoHashtagsOrURLs;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
