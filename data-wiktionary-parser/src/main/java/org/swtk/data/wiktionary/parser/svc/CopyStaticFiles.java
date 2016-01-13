package org.swtk.data.wiktionary.parser.svc;

import java.io.File;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public class CopyStaticFiles {

	public static LogManager logger = new LogManager(CopyStaticFiles.class);

	public static String WIKTIONARY_DB = "../data-wiktionary-parser/Library/template/WiktionaryDb.java";

	public static String WIKTIONARY_DB_OUT = "/Users/craigtrim/workspaces/public/swtk/commons/projects/commons-dict-wiktionary/src/main/java/org/swtk/commons/dict/wiktionary/WiktionaryDb.java";

	public static String WIKTIONARY_DB_UTILS = "../data-wiktionary-parser/Library/template/WiktionaryDbUtils.java";

	public static String WIKTIONARY_DB_UTILS_OUT = "/Users/craigtrim/workspaces/public/swtk/commons/projects/commons-dict-wiktionary/src/main/java/org/swtk/commons/dict/wiktionary/WiktionaryDbUtils.java";

	public static String WIKTIONARY_TERMS = "../data-wiktionary-parser/Library/template/WiktionaryTerms.java";

	public static String WIKTIONARY_TERMS_OUT = "/Users/craigtrim/workspaces/public/swtk/commons/projects/commons-dict-wiktionary/src/main/java/org/swtk/commons/dict/wiktionary/WiktionaryTerms.java";

	public static void process() throws BusinessException {

		try {

			org.apache.commons.io.FileUtils.copyFile(new File(WIKTIONARY_DB), new File(WIKTIONARY_DB_OUT));
			org.apache.commons.io.FileUtils.copyFile(new File(WIKTIONARY_DB_UTILS), new File(WIKTIONARY_DB_UTILS_OUT));
			org.apache.commons.io.FileUtils.copyFile(new File(WIKTIONARY_TERMS), new File(WIKTIONARY_TERMS_OUT));

		} catch (Exception e) {
			logger.error(e);
		}
	}
}
