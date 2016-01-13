package org.swtk.data.wiktionary.parser.dmo;

import java.io.File;
import java.util.List;

import org.swtk.common.dict.dto.wiktionary.Entry;
import org.swtk.common.framework.type.Alpha;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class JavaGenerator {

	public static LogManager logger = new LogManager(JavaGenerator.class);

	public static final String TEMPLATE = "../data-wiktionary-parser/Library/template/WiktionaryTemplate.java";

	public static final String BASE_OUT = "/Users/craigtrim/workspaces/public/swtk/commons/projects/commons-dict-wiktionary/src/main/java/org/swtk/commons/dict/wiktionary/generated/";

	public static final Integer THRESHOLD = 20;

	private String templateContents;

	private String asString(int num) {
		return StringUtils.pad(num, 3);
	}

	private String generateAddTemplate(Entry entry) {
		String json = GsonUtils.toJsonSingleLine(entry);
		json = StringUtils.replace(json, "\"", "\\\"");
		String code = String.format("\tadd(\"%s\", \"%s\");\n", entry.getTerm(), json);
		return code;
	}

	private String generateContent(List<Entry> entries, int num, Alpha... alphas) throws BusinessException {

		StringBuilder content = new StringBuilder();
		for (Entry entry : entries)
			content.append(generateAddTemplate(entry) + "\n");
		if (!StringUtils.hasValue(content.toString())) return null;

		StringBuilder alphaDot = new StringBuilder();
		for (int i = 0; i < alphas.length; i++) {
			alphaDot.append(alphas[i].lower());
			if (i + 1 < alphas.length) alphaDot.append(".");
		}

		StringBuilder alpha = new StringBuilder();
		for (Alpha a : alphas)
			alpha.append(a.upper());

		String template = getTemplateContents();
		try {
			
			template = StringUtils.replace(template, "#content", content.toString());
			template = StringUtils.replace(template, "#alphaDot", alphaDot.toString());
			template = StringUtils.replace(template, "#alpha", alpha.toString());
			template = StringUtils.replace(template, "#num", asString(num));
			
			/*template = template.replaceAll("#content", content.toString());
			template = template.replaceAll("#alphaDot", alphaDot.toString());
			template = template.replaceAll("#alpha", alpha.toString());
			template = template.replaceAll("#num", asString(num));*/
		} catch (Exception e) {
			logger.error(e);
			logger.info("Template Content:\n%s", content);
		}

		return template;
	}

	private String generateFileName(int num, Alpha... alphas) {
		StringBuilder sb = new StringBuilder();

		sb.append("Wiktionary");
		for (Alpha a : alphas)
			sb.append(a.upper());
		sb.append(asString(num));

		return sb.toString();
	}

	private String generateFilePath(String fileName, Alpha... alphas) {
		StringBuilder sb = new StringBuilder();

		sb.append(BASE_OUT);
		for (Alpha a : alphas)
			sb.append(a.lower() + "/");
		sb.append(fileName.toString());
		sb.append(".java");

		return sb.toString();
	}

	public String getTemplateContents() throws BusinessException {
		if (!StringUtils.hasValue(templateContents)) setTemplateContents(loadTemplateContents());
		return templateContents;
	}

	private String loadTemplateContents() throws BusinessException {
		return FileUtils.toString(new File(TEMPLATE), Codepage.UTF_8);
	}

	private void setTemplateContents(String templateContents) {
		this.templateContents = templateContents;
	}

	public String writeToFile(List<Entry> entries, Alpha... alphas) throws BusinessException {
		if (null == entries || entries.isEmpty()) return null;

		String fileName = generateFileName(0, alphas);
		String filePath = generateFilePath(fileName, alphas);

		String fileContent = generateContent(entries, 0, alphas);
		if (null == fileContent) return null;

		try {

			logger.info("Wrote to File (path = %s, size = %s)", filePath, entries.size());
			FileUtils.toFile(fileContent, filePath.toString(), Codepage.UTF_8);
			if (0 != fileContent.length()) return filePath;

		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return null;
	}
}
