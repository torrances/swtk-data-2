package org.swtk.data.twitter.core.dto.local;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.annotations.Expose;

/**
 * 	Purpose:
 * 	1.	the local format for tweets
 * 		if a twitter HTML page is saved locally, this format will represent the parse results
 * 
 */
public class LocalTweet {

	@Expose
	private Collection<LocalImage>	images;

	@Expose
	private Collection<LocalLink>	links;

	@Expose
	private String					text;

	public Collection<LocalImage> getImages() {
		if (null == images) setImages(new ArrayList<LocalImage>());
		return images;
	}

	public Collection<LocalLink> getLinks() {
		if (null == links) setLinks(new ArrayList<LocalLink>());
		return links;
	}

	public String getText() {
		return text;
	}

	public void setImages(Collection<LocalImage> images) {
		this.images = images;
	}

	public void setLinks(Collection<LocalLink> links) {
		this.links = links;
	}

	public void setText(String text) {
		this.text = text;
	}
}
