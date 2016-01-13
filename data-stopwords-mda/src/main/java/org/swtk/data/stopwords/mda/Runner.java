package org.swtk.data.stopwords.mda;

import org.swtk.data.stopwords.mda.svc.GenerateDictionaryByLanguage;

public final class Runner {

	public static void main(String... args) throws Throwable {
		GenerateDictionaryByLanguage.process();
	}
}
