package org.swtk.data.wiktionary.analyze.dto;

import com.trimc.blogger.commons.type.QuestionCategoryType;

public class QuestionAnalysis {

	private QuestionCategoryType questionCategoryType;
	
	private String subject;
	
	private String predicate;
	
	private String object;

	public String getObject() {
		return object;
	}

	public String getPredicate() {
		return predicate;
	}

	public QuestionCategoryType getQuestionCategoryType() {
		return questionCategoryType;
	}

	public String getSubject() {
		return subject;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public void setQuestionCategoryType(QuestionCategoryType questionCategoryType) {
		this.questionCategoryType = questionCategoryType;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
