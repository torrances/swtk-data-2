package org.swtk.commons.dict.wordnet.indexbyname.controller.#p1.#p2;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.common.dict.dto.wordnet.IndexNoun;

#imports

import com.trimc.blogger.commons.exception.BusinessException;

public final class WordnetNounIndexNameController#n1#n2
{

	public static Collection<IndexNoun> get(final String TERM) throws BusinessException {
		if (TERM.length() < 3) throw new BusinessException("TERM not found (term = %s)", TERM);
		
		String key = TERM.replaceAll(" ", "").substring(0, 3).toLowerCase();
		#content1

		throw new BusinessException("TERM not found (term = %s)", TERM);
	}

	public static Collection<String> terms() throws BusinessException {
		Set<String> set = new TreeSet<String>();

		#content2

		return set;
	}
}
