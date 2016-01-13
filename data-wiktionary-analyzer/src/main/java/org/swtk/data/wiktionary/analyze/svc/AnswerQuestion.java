package org.swtk.data.wiktionary.analyze.svc;

import org.swtk.common.dict.dto.wiktionary.Entry;
import org.swtk.commons.dict.wiktionary.WiktionaryDb;
import org.swtk.commons.dict.wiktionary.WiktionaryDbUtils;
import org.swtk.data.wiktionary.analyze.dmo.QuestionTypeAnalyzer;
import org.swtk.data.wiktionary.analyze.dto.QuestionAnalysis;
import org.swtk.data.wiktionary.analyze.dto.QuestionResult;
import org.swtk.data.wiktionary.analyze.dto.UserQuestion;
import org.swtk.data.wiktionary.analyze.dto.adapter.QuestionResultAdapter;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.QuestionCategoryType;

public class AnswerQuestion {

	public QuestionResult process(UserQuestion userQuestion) throws BusinessException {

		QuestionAnalysis questionAnalysis = new QuestionTypeAnalyzer().process(userQuestion);
		if (null == questionAnalysis) return QuestionResultAdapter.notUnderstood();

		if (QuestionCategoryType.Verification == questionAnalysis.getQuestionCategoryType()) {
			Entry entry = WiktionaryDb.findByName(questionAnalysis.getObject());
			if (null == entry) return QuestionResultAdapter.understoodButNoAnswer();
			return QuestionResultAdapter.answer(WiktionaryDbUtils.firstDefinition(entry.getTerm()).getText());
		}
		
		return QuestionResultAdapter.notUnderstood();
	}
}
