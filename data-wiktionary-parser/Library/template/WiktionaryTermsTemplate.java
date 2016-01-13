package org.swtk.commons.dict.wiktionary.terms;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

#imports

public final class WiktionaryTerms#alpha {

	public static Collection<String> terms() {
		Set<String> set = new TreeSet<String>();

		#content

		return set;
	}
}
