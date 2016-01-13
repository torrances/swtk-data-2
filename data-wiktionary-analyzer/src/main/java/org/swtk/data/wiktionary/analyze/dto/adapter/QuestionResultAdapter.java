package org.swtk.data.wiktionary.analyze.dto.adapter;

import org.swtk.data.wiktionary.analyze.dto.QuestionResult;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.MathUtils;

public final class QuestionResultAdapter {

	public static final String[] NOT_UNDERSTOOD = { "I did not understand your question", "I'm sorry, but I could not understand you", "I didn't understand your question", "I'm sorry - I couldn't understand you", "I was not able to understand your question" };

	public static final String[] NOT_ANSWERED = { "I understand what you're asking for, but I don't have an answer for that", "I don't have answer for that", "Your question was understood clearly, but I have no answer", "I don't have a definition for that term",
			"I'm sorry - I don't have a definition for that term" };

	public static final String[] ANSWERED = { "I found an answer in Wiktionary", "This is what I found from Wiktionary" };

	public static QuestionResult answer(String answer) throws AdapterValidationException {
		QuestionResult bean = new QuestionResult();

		bean.setQuestionUnderstood(true);
		bean.setQuestionAnswered(true);
		bean.setAnswer(answer);
		bean.setGloss(ANSWERED[MathUtils.random(0, ANSWERED.length - 1)]);

		return bean;
	}

	public static QuestionResult notUnderstood() throws AdapterValidationException {
		QuestionResult bean = new QuestionResult();

		bean.setQuestionUnderstood(false);
		bean.setQuestionAnswered(false);
		bean.setAnswer(null);
		bean.setGloss(NOT_UNDERSTOOD[MathUtils.random(0, NOT_UNDERSTOOD.length - 1)]);

		return bean;
	}

	public static String toLogString(QuestionResult result) {
		StringBuilder sb = new StringBuilder();

		sb.append("\n\tgloss = " + result.getGloss());
		sb.append("\n\tanswer = " + result.getAnswer());
		sb.append("\n\tquestion-answered = " + result.isQuestionAnswered());
		sb.append(", question-understood = " + result.isQuestionUnderstood());

		return sb.toString();
	}

	public static QuestionResult understoodButNoAnswer() throws AdapterValidationException {
		QuestionResult bean = new QuestionResult();

		bean.setQuestionUnderstood(true);
		bean.setQuestionAnswered(false);
		bean.setAnswer(null);
		bean.setGloss(NOT_ANSWERED[MathUtils.random(0, NOT_ANSWERED.length - 1)]);

		return bean;
	}
}
