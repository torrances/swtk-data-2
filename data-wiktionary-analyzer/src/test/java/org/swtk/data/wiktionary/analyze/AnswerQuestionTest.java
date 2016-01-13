package org.swtk.data.wiktionary.analyze;

import org.junit.Test;
import org.swtk.data.wiktionary.analyze.dto.QuestionResult;
import org.swtk.data.wiktionary.analyze.dto.UserQuestion;
import org.swtk.data.wiktionary.analyze.dto.adapter.QuestionResultAdapter;
import org.swtk.data.wiktionary.analyze.dto.adapter.UserQuestionAdapter;
import org.swtk.data.wiktionary.analyze.svc.AnswerQuestion;

import com.trimc.blogger.commons.LogManager;

public final class AnswerQuestionTest {

	public static LogManager logger = new LogManager(AnswerQuestionTest.class);

	@Test
	public void run() throws Throwable {
		run("what is a doghouse??");
	}

	private void run(String question) throws Throwable {
		UserQuestion userQuestion = UserQuestionAdapter.transform(question);
		QuestionResult result = new AnswerQuestion().process(userQuestion);
		logger.info("%s:%s", question, QuestionResultAdapter.toLogString(result));
	}
}
