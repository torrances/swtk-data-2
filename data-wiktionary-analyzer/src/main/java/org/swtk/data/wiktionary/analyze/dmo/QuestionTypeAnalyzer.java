package org.swtk.data.wiktionary.analyze.dmo;

import org.swtk.data.wiktionary.analyze.dto.QuestionAnalysis;
import org.swtk.data.wiktionary.analyze.dto.UserQuestion;
import org.swtk.data.wiktionary.analyze.dto.adapter.QuestionAnalysisAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class QuestionTypeAnalyzer {

	public static LogManager logger = new LogManager(QuestionTypeAnalyzer.class);

	public QuestionAnalysis process(UserQuestion userQuestion) throws BusinessException {
		
		String normalized = userQuestion.getNormalized();
		
		if (normalized.startsWith("what is a")) {
			String object = StringUtils.substringAfter(userQuestion.getNormalized(), "what is a ").trim();
			return QuestionAnalysisAdapter.definition(object);
		}
		
		else if (normalized.contains("origins of") || normalized.contains("etymology")) {
			
		}

		logger.warn("Question Analysis Failed:\n\tquestion = %s", userQuestion.getNormalized());
		return null;
	}
}
