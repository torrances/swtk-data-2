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

public class GenerateWiktionaryController {

	public static LogManager logger = new LogManager(GenerateWiktionaryController.class);

	public static final String TEMPLATE = "../data-wiktionary-parser/Library/template/WiktionaryDbTemplate.java";

	public static final String OUT = "/Users/craigtrim/workspaces/public/swtk/commons/projects/commons-dict-wiktionary/src/main/java/org/swtk/commons/dict/wiktionary/controller/WiktionaryDb%s.java";

	public static final String importTemplate = "import org.swtk.commons.dict.wiktionary.generated.%s.%s.%s.*;";

	public void process(Map<String, String> fileNamesMap) throws BusinessException {

		for (Alpha alpha : Alpha.values()) {

			boolean isFirst = true;
			StringBuilder sb = new StringBuilder();
			Set<String> imports = new TreeSet<String>();

			for (Map.Entry<String, String> entry : fileNamesMap.entrySet()) {
				String key = entry.getKey();
				if (!key.toLowerCase().startsWith(alpha.lower())) continue;
				sb.append("\t" + getTemplate(isFirst, key) + "\n");
				isFirst = false;
				imports.add(String.format(importTemplate, key.toCharArray()[0], key.toCharArray()[1], key.toCharArray()[2]));
			}

			if (0 == sb.length()) continue;

			String filename = String.format(OUT, alpha.upper());

			String contents = FileUtils.toString(new File(TEMPLATE), Codepage.UTF_8);
			contents = StringUtils.replace(contents, "#alpha", alpha.upper());
			contents = StringUtils.replace(contents, "#content", sb.toString());
			contents = StringUtils.replace(contents, "#imports", SetUtils.toString(imports, System.lineSeparator()));

			FileUtils.toFile(contents, new File(filename), Codepage.UTF_8);
			logger.info("Write to File (path = %s)", filename);
		}
	}

	private String getTemplate(boolean isFirst, String key) {
		final String template = "%s if (\"%s\".equals(sub)) return Wiktionary%s000.get(name);";
		return String.format(template, ((isFirst) ? "" : "else"), key.toLowerCase(), key.toUpperCase());
	}
}
