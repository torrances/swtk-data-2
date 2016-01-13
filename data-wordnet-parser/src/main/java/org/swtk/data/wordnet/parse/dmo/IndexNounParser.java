package org.swtk.data.wordnet.parse.dmo;

import org.swtk.common.dict.dto.wordnet.IndexNoun;
import org.swtk.common.dict.dto.wordnet.adapter.IndexNounAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public class IndexNounParser {

	public static LogManager logger = new LogManager(IndexNounParser.class);

	public IndexNoun process(String line) throws BusinessException {
		return IndexNounAdapter.transform(line);
	}
}
