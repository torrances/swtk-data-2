package org.swtk.commons.dict.wordnet.indexbyname.controller;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.common.dict.dto.wordnet.IndexNoun;

#imports

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.LogManager;

public class WordnetNounIndexNameController {

	public static LogManager logger = new LogManager(WordnetNounIndexNameController.class);

	public static Collection<IndexNoun> get(final String TERM) throws BusinessException {

		String key = TERM.substring(0, 2).toLowerCase();
		#content1

		throw new BusinessException("TERM not found (term = %s)", TERM);
	}

	public static Collection<String> terms() throws BusinessException {
		Set<String> set = new TreeSet<String>();

		#content2

		return set;
	}
}
