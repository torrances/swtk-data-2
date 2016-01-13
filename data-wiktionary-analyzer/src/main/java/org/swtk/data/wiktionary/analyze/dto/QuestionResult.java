package org.swtk.data.wiktionary.analyze.dto;

public class QuestionResult {

	private String answer;

	private boolean questionUnderstood;

	private boolean questionAnswered;

	private String gloss;

	public String getAnswer() {
		return answer;
	}

	public String getGloss() {
		return gloss;
	}

	public boolean isQuestionAnswered() {
		return questionAnswered;
	}

	public boolean isQuestionUnderstood() {
		return questionUnderstood;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setGloss(String gloss) {
		this.gloss = gloss;
	}

	public void setQuestionAnswered(boolean questionAnswered) {
		this.questionAnswered = questionAnswered;
	}

	public void setQuestionUnderstood(boolean questionUnderstood) {
		this.questionUnderstood = questionUnderstood;
	}
}
