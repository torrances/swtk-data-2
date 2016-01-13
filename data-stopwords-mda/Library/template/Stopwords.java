package org.swtk.commons.dict.stopwords.generated;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Stopwords#lang {

	private static Set<String> set = new TreeSet<String>();

	static {
		#content
	}

	private static void add(String term) {
		set.add(term.toLowerCase());
	}

	public static boolean has(String term) {
		return set.contains(term.toLowerCase());
	}

	public static Collection<String> terms() {
		return set;
	}
}