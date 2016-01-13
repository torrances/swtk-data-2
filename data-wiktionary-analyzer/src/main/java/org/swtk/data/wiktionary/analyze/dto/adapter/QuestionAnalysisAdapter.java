package org.swtk.data.wiktionary.analyze.dto.adapter;

import org.swtk.data.wiktionary.analyze.dto.QuestionAnalysis;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.type.QuestionCategoryType;

public final class QuestionAnalysisAdapter {

	public static QuestionAnalysis conceptCompletion(String term) throws AdapterValidationException {
		QuestionAnalysis bean = new QuestionAnalysis();
		
		bean.setQuestionCategoryType(QuestionCategoryType.ConceptCompletion);
		bean.setObject(term);

		return bean;
	}
	public static QuestionAnalysis definition(String term) throws AdapterValidationException {
		QuestionAnalysis bean = new QuestionAnalysis();
		
		bean.setQuestionCategoryType(QuestionCategoryType.Definition);
		bean.setObject(term);

		return bean;
	}
}
