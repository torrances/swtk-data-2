package org.swtk.data.rt.core.dto;

import org.swtk.data.rt.core.type.Sentiment;

import com.google.gson.annotations.Expose;

public class Score {

	@Expose
	private Double min;

	@Expose
	private Double max;

	@Expose
	private Sentiment sentiment;

	public Double getMax() {
		return max;
	}

	public Double getMin() {
		return min;
	}

	public Sentiment getSentiment() {
		return sentiment;
	}

	public boolean hasMax() {
		return null != getMax();
	}

	public boolean hasMin() {
		return null != getMin();
	}

	public boolean hasSentiment() {
		return null != getSentiment();
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}
}
