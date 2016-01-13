package org.swtk.data.wordnet.mda.svc;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.swtk.common.dict.dto.wordnet.IndexNoun;
import org.swtk.data.wordnet.mda.utils.WordnetMdaConstants;
import org.swtk.data.wordnet.parse.dmo.IndexNounParser;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class GenerateIndexByIdInstances {

	public static LogManager logger = new LogManager(GenerateIndexByIdInstances.class);

	private static Map<String, Map<String, Collection<IndexNoun>>> map = new TreeMap<String, Map<String, Collection<IndexNoun>>>();

	public static Set<String> idSet = new TreeSet<String>();

	public static String JAVA_ADD_PATTERN = "\tadd(\"%s\", \"%s\");\n";
	
	public static File SOURCE = WordnetMdaConstants.SrcFileIndexNoun;

	public static File TEMPLATE = WordnetMdaConstants.TemplateIndexByIdInstance;

	private static void add(final String ID, final String JSON) {
		IndexNoun indexNoun = GsonUtils.toObject(JSON, IndexNoun.class);

		String key = ID.substring(0, 4);
		Map<String, Collection<IndexNoun>> innerMap = map.containsKey(key) ? map.get(key) : new TreeMap<String, Collection<IndexNoun>>();
		Collection<IndexNoun> innerMostList = (innerMap.containsKey(ID)) ? innerMap.get(ID) : new ArrayList<IndexNoun>();

		innerMostList.add(indexNoun);
		innerMap.put(ID, innerMostList);
		map.put(key, innerMap);
	}

	private static String getContents(Map<String, Collection<IndexNoun>> map) {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, Collection<IndexNoun>> e2 : map.entrySet()) {
			for (IndexNoun bean : e2.getValue()) {
				String json = GsonUtils.toJsonSingleLine(bean);
				json = StringUtils.replace(json, "\"", "\\\"");
				sb.append(String.format(JAVA_ADD_PATTERN, e2.getKey(), json));
			}
		}

		return sb.toString();
	}

	public static void process() throws BusinessException {

		if (!SOURCE.exists()) throw new BusinessException("Input file does not exist (path = %s)", SOURCE.getAbsolutePath());
		if (!TEMPLATE.exists()) throw new BusinessException("Template file does not exist (path = %s)", TEMPLATE.getAbsolutePath());

		for (String line : FileUtils.toList(SOURCE, Codepage.UTF_8)) {
			IndexNoun bean = new IndexNounParser().process(line);

			String json = GsonUtils.toJsonSingleLine(bean);
			for (String id : bean.getIds())
				add(id, json);
		}

		for (Map.Entry<String, Map<String, Collection<IndexNoun>>> e1 : map.entrySet()) {

			String key = e1.getKey();

			String outFileName = WordnetMdaConstants.OutFileIndexByIdInstance;
			outFileName = StringUtils.replace(outFileName, "#n1", key.substring(0, 1).toUpperCase());
			outFileName = StringUtils.replace(outFileName, "#n2", key.substring(1, 2).toUpperCase());
			outFileName = StringUtils.replace(outFileName, "#n3", key.substring(2, 3).toUpperCase());
			outFileName = StringUtils.replace(outFileName, "#n4", key.substring(3, 4).toUpperCase());

			String outPkgName = WordnetMdaConstants.OutPathIndexByIdInstance;
			outPkgName = StringUtils.replace(outPkgName, "#n1", key.substring(0, 1));
			outPkgName = StringUtils.replace(outPkgName, "#n2", key.substring(1, 2));

			String template = FileUtils.toString(TEMPLATE, Codepage.UTF_8);
			template = StringUtils.replace(template, "#n1", key.substring(0, 1));
			template = StringUtils.replace(template, "#n2", key.substring(1, 2));
			template = StringUtils.replace(template, "#n3", key.substring(2, 3));
			template = StringUtils.replace(template, "#n4", key.substring(3, 4));
			template = StringUtils.replace(template, "#content", getContents(e1.getValue()));

			String filename = outPkgName + outFileName;
			FileUtils.toFile(template, filename, Codepage.UTF_8);
			logger.info("Wrote to file (path = %s, total-entries = %s)", filename, StringUtils.format(e1.getValue().size()));
		}
	}
}
