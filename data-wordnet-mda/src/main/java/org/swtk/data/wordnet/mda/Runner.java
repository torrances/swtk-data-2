package org.swtk.data.wordnet.mda;

import java.util.Set;

import org.swtk.data.wordnet.mda.svc.GenerateIndexByNameControllerMeta;
import org.swtk.data.wordnet.mda.svc.GenerateIndexByNameControllers;
import org.swtk.data.wordnet.mda.svc.GenerateIndexByNameInstances;

public final class Runner {

	public static void main(String... args) throws Throwable {

		//		GenerateIndexByIdInstances.process();
		//		GenerateIndexByIdControllers.process();

		GenerateIndexByNameInstances.process();
		Set<String> alphasSet = GenerateIndexByNameControllers.process();
		GenerateIndexByNameControllerMeta.process(alphasSet);

		//		GenerateNounFrameInstances.process();
		//		GenerateNounFrameControllers.process();
	}
}
