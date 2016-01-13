package org.swtk.data.wiktionary.parser;

import org.swtk.data.wiktionary.parser.svc.CopyStaticFiles;
import org.swtk.data.wiktionary.parser.svc.GenerateWiktionaryClasses;
import org.swtk.data.wiktionary.parser.svc.GenerateWiktionaryController;
import org.swtk.data.wiktionary.parser.svc.GenerateWiktionaryTerms;

public class Runner {

	public static void main(String... args) throws Throwable {

		GenerateWiktionaryClasses clazzGenerator = new GenerateWiktionaryClasses();
		clazzGenerator.process();

		GenerateWiktionaryController cntrlGenerator = new GenerateWiktionaryController();
		cntrlGenerator.process(clazzGenerator.fileNamesMap);

		GenerateWiktionaryTerms termsGenerator = new GenerateWiktionaryTerms();
		termsGenerator.process(clazzGenerator.fileNamesMap);

		CopyStaticFiles.process();
	}
}
