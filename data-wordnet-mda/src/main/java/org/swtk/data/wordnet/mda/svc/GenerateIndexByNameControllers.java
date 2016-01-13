package org.swtk.data.wordnet.mda.svc;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.swtk.common.dict.dto.wordnet.IndexNoun;
import org.swtk.common.framework.type.Alpha;
import org.swtk.data.wordnet.mda.utils.IndexByNameControllerHelper;
import org.swtk.data.wordnet.mda.utils.WordnetMdaConstants;
import org.swtk.data.wordnet.parse.dmo.IndexNounParser;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class GenerateIndexByNameControllers {

	public static LogManager logger = new LogManager(GenerateIndexByNameControllers.class);

	public static String JAVA_ADD_PATTERN_01 = "\tif (\"%s\".equals(key)) return WordnetNounIndexNameInstance%s.get(TERM);\n";

	public static final String JAVA_ADD_PATTERN_02 = "\tset.addAll(WordnetNounIndexNameInstance%s.terms());\n";

	public static File TEMPLATE = WordnetMdaConstants.TemplateIndexByNameController;

	public static File SOURCE = WordnetMdaConstants.SrcFileIndexNoun;

	public static final String JAVA_IMPORT_PATTERN = "\timport org.swtk.commons.dict.wordnet.indexbyname.instance.#p1.#p2.#p3.WordnetNounIndexNameInstance#n1#n2#n3;\n";

	private static Set<String> generate(Map<Alpha, Map<Alpha, Collection<String>>> termsMap) throws BusinessException {
		Set<String> alphasSet = new TreeSet<String>();

		for (Map.Entry<Alpha, Map<Alpha, Collection<String>>> e1 : termsMap.entrySet()) {
			Alpha alpha1 = e1.getKey();

			for (Map.Entry<Alpha, Collection<String>> e2 : e1.getValue().entrySet()) {
				Alpha alpha2 = e2.getKey();

				StringBuilder sbImports = new StringBuilder();
				StringBuilder sbContents01 = new StringBuilder();
				StringBuilder sbContents02 = new StringBuilder();

				for (String term : e2.getValue()) {
					sbContents01.append(String.format(JAVA_ADD_PATTERN_01, term, term.toUpperCase()));
					sbContents02.append(String.format(JAVA_ADD_PATTERN_02, term.toUpperCase()));
					sbImports.append(IndexByNameControllerHelper.getImportPattern(JAVA_IMPORT_PATTERN, term));
				}

				String outFileName = IndexByNameControllerHelper.getOutFileName(WordnetMdaConstants.OutFileIndexByNameController, alpha1, alpha2);
				String outPkgName = IndexByNameControllerHelper.getOutPkgName(WordnetMdaConstants.OutPathIndexByNameController, alpha1, alpha2);
				String template = IndexByNameControllerHelper.getTemplateContents(TEMPLATE, alpha1, alpha2, sbContents01, sbContents02, sbImports);

				String filename = outPkgName + outFileName;
				FileUtils.toFile(template, filename, Codepage.UTF_8);
				logger.info("Wrote to file (path = %s, total-entries = %s)", filename, StringUtils.format(e2.getValue().size()));

				alphasSet.add(alpha1.lower() + alpha2.lower());
			}
		}

		return alphasSet;
	}

	private static Map<Alpha, Map<Alpha, Collection<String>>> group() throws BusinessException {
		Map<Alpha, Map<Alpha, Collection<String>>> termsMap = new TreeMap<Alpha, Map<Alpha, Collection<String>>>();

		for (Alpha alpha1 : Alpha.values()) {
			Map<Alpha, Collection<String>> innerMap = new TreeMap<Alpha, Collection<String>>();

			for (Alpha alpha2 : Alpha.values()) {
				Set<String> innerInnerSet = new TreeSet<String>();

				for (String line : FileUtils.toList(SOURCE, Codepage.UTF_8)) {
					IndexNoun bean = new IndexNounParser().process(line);

					String term = bean.getTerm().toLowerCase();
					if (term.length() <= 3) continue;
					if (!TextUtils.isAlphaOnly(term)) continue;
					if (!term.substring(0, 1).startsWith(alpha1.lower())) continue;
					if (!term.substring(1, 2).startsWith(alpha2.lower())) continue;

					String termSubstring = null;
					if (term.length() >= 3) termSubstring = bean.getTerm().substring(0, 3);
					else continue;

					innerInnerSet.add(termSubstring);
				}

				if (!innerInnerSet.isEmpty()) innerMap.put(alpha2, innerInnerSet);
				logger.info("Completed Processing (alpha-1 = %s, alpha-2 = %s)", alpha1, alpha2);
			}

			termsMap.put(alpha1, innerMap);
		}

		return termsMap;
	}

	public static Set<String> process() throws BusinessException {

		/*	check if essential files exist */
		if (!SOURCE.exists()) throw new BusinessException("Input file does not exist (path = %s)", SOURCE.getAbsolutePath());
		if (!TEMPLATE.exists()) throw new BusinessException("Template file does not exist (path = %s)", TEMPLATE.getAbsolutePath());

		return generate(group());
	}
}
