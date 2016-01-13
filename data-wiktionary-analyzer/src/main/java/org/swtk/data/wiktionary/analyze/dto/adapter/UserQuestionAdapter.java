package org.swtk.data.wiktionary.analyze.dto.adapter;

import org.swtk.data.wiktionary.analyze.dto.UserQuestion;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class UserQuestionAdapter {

	public static LogManager logger = new LogManager(UserQuestionAdapter.class);

	public static UserQuestion transform(String question) throws AdapterValidationException {
		UserQuestion bean = new UserQuestion();

		String normalized = StringUtils.trim(question).toLowerCase();
		while (TextUtils.endsWithPunctuation(normalized))
			normalized = normalized.substring(0, normalized.length() - 1);
		while (TextUtils.startsWithPunctuation(normalized))
			normalized = normalized.substring(1, normalized.length());

		bean.setOriginal(question);
		bean.setNormalized(normalized);

		if (!bean.getOriginal().equalsIgnoreCase(bean.getNormalized())) {
			logger.info("User Question:\n\toriginal = %s\n\tnormalized = %s", bean.getOriginal(), bean.getNormalized());
		} else {
			logger.info("User Question:\n\toriginal = %s\n\tnormalized = %s", bean.getOriginal(), "(unchanged)");
		}
		
		return bean;
	}
}
