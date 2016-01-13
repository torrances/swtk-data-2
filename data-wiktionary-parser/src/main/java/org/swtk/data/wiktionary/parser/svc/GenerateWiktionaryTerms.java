package org.swtk.data.wiktionary.parser.svc;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.common.framework.type.Alpha;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.SetUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class GenerateWiktionaryTerms {

	public static LogManager logger = new LogManager(GenerateWiktionaryTerms.class);

	public static String TEMPLATE = "../data-wiktionary-parser/Library/template/WiktionaryTermsTemplate.java";

	public static String OUT = "/Users/craigtrim/workspaces/public/swtk/commons/projects/commons-dict-wiktionary/src/main/java/org/swtk/commons/dict/wiktionary/terms/WiktionaryTerms%s.java";

	public static final String EMIT_TEMPLATE_01 = "set.addAll(%s.terms());";

	public static final String importTemplate = "import org.swtk.commons.dict.wiktionary.generated.%s.%s.%s.*;";

	private String emitter(String filename) {
		filename = StringUtils.substringBefore(StringUtils.substringAfterLast(filename, "/"), ".");
		return String.format(EMIT_TEMPLATE_01, filename);
	}

	public void process(Map<String, String> fileNamesMap) throws BusinessException {

		File IN = new File(TEMPLATE);
		if (!IN.exists()) throw new BusinessException("Template does not exist (path = %s)", IN.getAbsolutePath());

		for (Alpha alpha : Alpha.values()) {

			String contents = FileUtils.toString(IN, Codepage.UTF_8);
			if (!StringUtils.hasValue(contents)) throw new BusinessException("Template is empty (path = %s)", IN.getAbsolutePath());

			Set<String> importsSet = new TreeSet<String>();
			Set<String> emitterSet = new TreeSet<String>();

			for (Map.Entry<String, String> entry : fileNamesMap.entrySet()) {
				String key = entry.getKey();
				if (!key.toLowerCase().startsWith(alpha.lower())) continue;
				emitterSet.add(emitter(entry.getValue()));
				importsSet.add(String.format(importTemplate, key.toCharArray()[0], key.toCharArray()[1], key.toCharArray()[2]));
			}

			String theGeneratedContent = StringUtils.toString(emitterSet, System.lineSeparator());
			contents = StringUtils.replace(contents, "#alpha", alpha.upper());
			contents = StringUtils.replace(contents, "#content", theGeneratedContent);
			contents = StringUtils.replace(contents, "#imports", SetUtils.toString(importsSet, System.lineSeparator()));

			String theOutName = String.format(OUT, alpha.upper());

			FileUtils.toFile(contents, theOutName, Codepage.UTF_8);
			logger.info("Wrote to File (path = %s)", OUT);
		}
	}
}
