package org.swtk.data.wordnet.mda.svc;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.swtk.common.dict.dto.wordnet.DataNoun;
import org.swtk.data.wordnet.mda.utils.WordnetMdaConstants;
import org.swtk.data.wordnet.parse.dmo.DataNounParser;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class GenerateNounFrameInstances {

	public static LogManager logger = new LogManager(GenerateNounFrameInstances.class);

	private static Map<String, Map<String, Collection<DataNoun>>> map = new TreeMap<String, Map<String, Collection<DataNoun>>>();

	public static String JAVA_ADD_PATTERN = "\tadd(\"%s\");\n";
	
	public static File SOURCE = WordnetMdaConstants.SrcFileIndexNoun;

	private static void add(final String ID, final String JSON) {
		DataNoun bean = GsonUtils.toObject(JSON, DataNoun.class);

		String key = ID.substring(0, 4);
		Map<String, Collection<DataNoun>> innerMap = map.containsKey(key) ? map.get(key) : new TreeMap<String, Collection<DataNoun>>();
		Collection<DataNoun> innerMostList = (innerMap.containsKey(ID)) ? innerMap.get(ID) : new ArrayList<DataNoun>();

		innerMostList.add(bean);
		innerMap.put(ID, innerMostList);
		map.put(key, innerMap);
	}

	private static String getContents(Map<String, Collection<DataNoun>> map) {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, Collection<DataNoun>> e2 : map.entrySet()) {
			for (DataNoun bean : e2.getValue()) {
				String json = GsonUtils.toJsonSingleLine(bean);
				json = StringUtils.replace(json, "\"", "\\\"");
				sb.append(String.format(JAVA_ADD_PATTERN, json));
			}
		}

		return sb.toString();
	}

	public static void process() throws BusinessException {
		if (!WordnetMdaConstants.SrcFileDataNoun.exists()) throw new BusinessException("Input file does not exist (path = %s)", SOURCE.getAbsolutePath());

		for (String line : FileUtils.toList(WordnetMdaConstants.SrcFileDataNoun, Codepage.UTF_8)) {
			if (!StringUtils.hasValue(line)) continue;

			DataNoun bean = new DataNounParser().process(line);
			String json = GsonUtils.toJsonSingleLine(bean);

			add(bean.getId(), json);
		}

		logger.info("Populated Beans (total = %s)", StringUtils.format(map.size()));

		for (Map.Entry<String, Map<String, Collection<DataNoun>>> e1 : map.entrySet()) {

			String key = e1.getKey();

			String outFileName = WordnetMdaConstants.OutFileFrameInstance;
			outFileName = StringUtils.replace(outFileName, "#n1", key.substring(0, 1).toUpperCase());
			outFileName = StringUtils.replace(outFileName, "#n2", key.substring(1, 2).toUpperCase());
			outFileName = StringUtils.replace(outFileName, "#n3", key.substring(2, 3).toUpperCase());
			outFileName = StringUtils.replace(outFileName, "#n4", key.substring(3, 4).toUpperCase());

			String outPkgName = WordnetMdaConstants.OutPathNounFrameInstance;
			outPkgName = StringUtils.replace(outPkgName, "#n1", key.substring(0, 1));
			outPkgName = StringUtils.replace(outPkgName, "#n2", key.substring(1, 2));

			String template = FileUtils.toString(WordnetMdaConstants.TemplateNounFrameInstance, Codepage.UTF_8);
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
