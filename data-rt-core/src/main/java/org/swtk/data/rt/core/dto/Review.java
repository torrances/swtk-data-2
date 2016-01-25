package org.swtk.data.rt.core.dto;

import com.google.gson.annotations.Expose;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class Review {

	@Expose
	private Critic critic;
	
	@Expose
	private String date;
	
	@Expose
	private String fullReviewLink;
	
	@Expose
	private String blurb;
	
	@Expose
	private Score score;
	
	public String getBlurb() {
		return blurb;
	}
	
	public Critic getCritic() {
		return critic;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getFullReviewLink() {
		return fullReviewLink;
	}
	
	public Score getScore() {
		return score;
	}

	public boolean hasBlurb() {
		return StringUtils.hasValue(getBlurb());
	}

	public boolean hasCritic() {
		return null != getCritic();
	}

	public boolean hasDate() {
		return StringUtils.hasValue(getDate());
	}

	public boolean hasFullReviewLink() {
		return StringUtils.hasValue(getFullReviewLink());
	}

	public boolean hasScore() {
		return null != getScore();
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public void setCritic(Critic critic) {
		this.critic = critic;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setFullReviewLink(String fullReviewLink) {
		this.fullReviewLink = fullReviewLink;
	}

	public void setScore(Score score) {
		this.score = score;
	}
}
