package org.swtk.data.rt.core.dto.adapter;

import org.swtk.data.rt.core.dto.Review;

public final class ReviewAdapter {

	public static String toString(Review review) {
		StringBuilder sb = new StringBuilder();

		if (review.hasCritic()) sb.append("\n\tcritic = " + CriticAdapter.toString(review.getCritic()));
		if (review.hasDate()) sb.append("\n\tdate = " + review.getDate());
		if (review.hasBlurb()) sb.append("\n\tblurb = " + review.getBlurb());
		if (review.hasFullReviewLink()) sb.append("\n\tlink = " + review.getFullReviewLink());
		if (review.hasScore()) sb.append("\n\tscore = " + ScoreAdapter.toString(review.getScore()));

		return sb.toString();
	}
}
