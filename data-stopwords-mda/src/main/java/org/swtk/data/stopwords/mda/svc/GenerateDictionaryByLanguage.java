package org.swtk.data.stopwords.mda.svc;

import java.io.File;
import java.util.Collection;

import org.swtk.common.framework.type.LanguageTag;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class GenerateDictionaryByLanguage {

	public static LogManager logger = new LogManager(GenerateDictionaryByLanguage.class);

	public static String JAVA_ADD_PATTERN = "\tadd(\"%s\");\n";

	public static String SOURCE = "../data-stopwords-core/src/main/resources/stop-words.%s";

	public static File TEMPLATE = new File("../data-stopwords-mda/Library/template/Stopwords.java");

	public static String GENERATED = "../data-stopwords-mda/Library/generated/Stopwords%s.java";

	public static void process() throws BusinessException {
		for (LanguageTag value : LanguageTag.values()) {

			File source = new File(String.format(SOURCE, value.toString()));
			if (!source.exists()) continue;

			process(value, source);
		}
	}

	public static void process(LanguageTag languageTag, File source) throws BusinessException {
		StringBuilder sb = new StringBuilder();

		Collection<String> terms = FileUtils.toList(source, Codepage.UTF_8);
		for (String term : terms)
			sb.append(String.format(JAVA_ADD_PATTERN, term));
		
		String template = FileUtils.toString(TEMPLATE, Codepage.UTF_8);
		template = template.replaceAll("#lang", StringUtils.capitalize(languageTag.toString()));
		template = template.replaceAll("#content", sb.toString());

		File generated = new File(String.format(GENERATED, StringUtils.capitalize(languageTag.toString())));
		FileUtils.toFile(template, generated, Codepage.UTF_8);
		logger.info("Wrote to file (path = %s, total-entries = %s)", generated.getAbsolutePath(), terms.size());
	}
}
