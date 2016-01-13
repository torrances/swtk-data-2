package org.swtk.commons.dict.wiktionary;

import org.swtk.common.dict.dto.wiktionary.Definition;
import org.swtk.common.dict.dto.wiktionary.Entry;
import org.swtk.common.dict.dto.wiktionary.iter.Definitions;

public final class WiktionaryDbUtils {

	public static Definitions definitions(String term) {
		Entry entry = WiktionaryDb.findByName(term);
		return (null != entry) ? entry.getDefinitions() : null;
	}

	public static Definition firstDefinition(String term) {
		Definitions definitions = definitions(term);
		return (null != definitions) ? definitions.iterator().next() : null;
	}
}
