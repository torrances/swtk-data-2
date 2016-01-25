package org.swtk.data.rt.core.dto.adapter;

import org.swtk.data.rt.core.dto.Score;
import org.swtk.data.rt.core.type.Sentiment;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class ScoreAdapter {

	private static Sentiment getSentiment(Double min, Double max) {
		double result = ((double) min) / ((double) max);
		if (result >= 0.90) return Sentiment.HIGH;
		if (result >= 0.75) return Sentiment.MEDIUM_HIGH;
		if (result > 0.40) return Sentiment.MEDIUM;
		if (result <= 0.40 && result > 0.20) return Sentiment.MEDIUM_LOW;
		if (result <= 0.20) return Sentiment.LOW;

		return Sentiment.UNKNOWN;
	}

	public static final String toString(Score score) {
		StringBuilder sb = new StringBuilder();

		sb.append("min = " + score.getMin());
		sb.append(", max = " + score.getMax());
		sb.append(", sentiment = " + score.getSentiment());

		return sb.toString();
	}

	public static Score transform(Double min, Double max) throws AdapterValidationException {
		return transform(min, max, getSentiment(min, max));
	}

	public static Score transform(Double min, Double max, Sentiment sentiment) throws AdapterValidationException {
		Score score = new Score();

		score.setMin(min);
		score.setMax(max);
		score.setSentiment(sentiment);

		return score;
	}

	public static Score transform(String min, String max) throws AdapterValidationException {
		try {
			return transform(Double.parseDouble(min), Double.parseDouble(max));
		} catch (NumberFormatException e) {
			throw new AdapterValidationException("Expected Double Values (min = %s, max = %s)", min, max);
		}
	}
}
