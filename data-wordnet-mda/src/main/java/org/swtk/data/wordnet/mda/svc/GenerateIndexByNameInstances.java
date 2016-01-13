package org.swtk.data.wordnet.mda.svc;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.swtk.common.dict.dto.wordnet.IndexNoun;
import org.swtk.data.wordnet.mda.utils.IndexByNameInstanceHelper;
import org.swtk.data.wordnet.mda.utils.WordnetMdaConstants;
import org.swtk.data.wordnet.parse.dmo.IndexNounParser;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class GenerateIndexByNameInstances {

	public static LogManager logger = new LogManager(GenerateIndexByNameInstances.class);

	private static Map<String, Map<String, Collection<IndexNoun>>> map = new TreeMap<String, Map<String, Collection<IndexNoun>>>();

	public static String JAVA_ADD_PATTERN = "\tadd(\"%s\");\n";

	public static File TEMPLATE = WordnetMdaConstants.TemplateIndexByNameInstance;

	public static File SOURCE = WordnetMdaConstants.SrcFileIndexNoun;

	private static void add(final String TERM, IndexNoun indexNoun) {

		String key = (TERM.length() >= 3) ? TERM.replaceAll(" ", "").substring(0, 3) : TERM;
		Map<String, Collection<IndexNoun>> innerMap = map.containsKey(key) ? map.get(key) : new TreeMap<String, Collection<IndexNoun>>();
		Collection<IndexNoun> innerMostList = (innerMap.containsKey(TERM)) ? innerMap.get(TERM) : new ArrayList<IndexNoun>();

		innerMostList.add(indexNoun);
		innerMap.put(TERM, innerMostList);
		map.put(key, innerMap);
	}

	private static String getContents(Map<String, Collection<IndexNoun>> map) {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, Collection<IndexNoun>> e2 : map.entrySet()) {
			for (IndexNoun bean : e2.getValue()) {
				String json = GsonUtils.toJsonSingleLine(bean);
				json = StringUtils.replace(json, "\"", "\\\"");
				sb.append(String.format(JAVA_ADD_PATTERN, json));
			}
		}

		return sb.toString();
	}

	public static void process() throws BusinessException {

		if (!SOURCE.exists()) throw new BusinessException("Input file does not exist (path = %s)", SOURCE.getAbsolutePath());
		if (!TEMPLATE.exists()) throw new BusinessException("Template file does not exist (path = %s)", TEMPLATE.getAbsolutePath());

		for (String line : FileUtils.toList(SOURCE, Codepage.UTF_8)) {
			IndexNoun bean = new IndexNounParser().process(line);
			if (!TextUtils.isAlphaOnly(bean.getTerm(), ' ')) continue;
			if (bean.getTerm().length() < 3) continue;
			add(bean.getTerm(), bean);
		}

		for (Map.Entry<String, Map<String, Collection<IndexNoun>>> e1 : map.entrySet()) {

			String key = e1.getKey();

			String outFileName = IndexByNameInstanceHelper.getOutFilename(WordnetMdaConstants.OutFileIndexByNameInstance, key);
			String outPkgName = IndexByNameInstanceHelper.getOutPackageName(WordnetMdaConstants.OutPathIndexByNameInstance, key);
			String template = IndexByNameInstanceHelper.getTemplateClassName(TEMPLATE, key);

			template = StringUtils.replace(template, "#content", getContents(e1.getValue()));

			String filename = outPkgName + outFileName;
			FileUtils.toFile(template, filename, Codepage.UTF_8);
			logger.info("Wrote to file (path = %s, total-entries = %s)", filename, StringUtils.format(e1.getValue().size()));
		}
	}
}
