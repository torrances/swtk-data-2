package org.swtk.commons.dict.wiktionary.controller;

import org.swtk.common.dict.dto.wiktionary.Entry;

#imports


public final class WiktionaryDb#alpha {

	public static Entry findByName(String name) {
		if (name.length() < 4) return null;

		String sub = name.substring(0, 3).toLowerCase();
		#content

		return null;
	}
}
