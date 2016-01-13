package org.swtk.data.wordnet.mda.svc;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.swtk.common.framework.type.Alpha;
import org.swtk.data.wordnet.mda.utils.WordnetMdaConstants;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class GenerateIndexByNameControllerMeta {

	public static LogManager logger = new LogManager(GenerateIndexByNameControllerMeta.class);

	public static Map<Alpha, Map<Alpha, Collection<String>>> termsMap = new TreeMap<Alpha, Map<Alpha, Collection<String>>>();

	public static String JAVA_ADD_PATTERN_01 = "\tif (\"#n1#n2\".equals(key)) return WordnetNounIndexNameController#N1#N2.get(TERM);\n";

	public static File TEMPLATE = WordnetMdaConstants.TemplateIndexByNameControllerMeta;

	public static final String JAVA_IMPORT_PATTERN = "\timport org.swtk.commons.dict.wordnet.indexbyname.controller.#p1.#p2.WordnetNounIndexNameController#N1#N2;\n";

	public static final String JAVA_ADD_PATTERN_02 = "\tset.addAll(WordnetNounIndexNameController#N1#N2.terms());\n";

	public static void process(Set<String> alphasSet) throws BusinessException {

		StringBuilder sbContents01 = new StringBuilder();
		StringBuilder sbContents02 = new StringBuilder();
		StringBuilder sbImports = new StringBuilder();

		for (String alpha : alphasSet) {
			String contentsTemplate01 = JAVA_ADD_PATTERN_01;
			String contentsTemplate02 = JAVA_ADD_PATTERN_02;
			String importsTemplate = JAVA_IMPORT_PATTERN;

			String a1 = alpha.substring(0, 1);
			String a2 = alpha.substring(1, 2);

			contentsTemplate01 = StringUtils.replace(contentsTemplate01, "#n1", a1.toLowerCase());
			contentsTemplate01 = StringUtils.replace(contentsTemplate01, "#n2", a2.toLowerCase());

			contentsTemplate01 = StringUtils.replace(contentsTemplate01, "#N1", a1.toUpperCase());
			contentsTemplate01 = StringUtils.replace(contentsTemplate01, "#N2", a2.toUpperCase());

			contentsTemplate02 = StringUtils.replace(contentsTemplate02, "#N1", a1.toUpperCase());
			contentsTemplate02 = StringUtils.replace(contentsTemplate02, "#N2", a2.toUpperCase());

			importsTemplate = StringUtils.replace(importsTemplate, "#p1", a1.toLowerCase());
			importsTemplate = StringUtils.replace(importsTemplate, "#p2", a2.toLowerCase());

			importsTemplate = StringUtils.replace(importsTemplate, "#N1", a1.toUpperCase());
			importsTemplate = StringUtils.replace(importsTemplate, "#N2", a2.toUpperCase());

			sbImports.append(importsTemplate);
			sbContents01.append(contentsTemplate01);
			sbContents02.append(contentsTemplate02);
		}

		String outFileName = WordnetMdaConstants.OutFileIndexByNameControllerMeta;
		String outPkgName = WordnetMdaConstants.OutPathIndexByNameMetaController;

		String template = FileUtils.toString(TEMPLATE, Codepage.UTF_8);
		template = StringUtils.replace(template, "#content1", sbContents01.toString());
		template = StringUtils.replace(template, "#content2", sbContents02.toString());
		template = StringUtils.replace(template, "#imports", sbImports.toString());

		String filename = outPkgName + outFileName;
		FileUtils.toFile(template, filename, Codepage.UTF_8);
		logger.info("Wrote to file (path = %s, total-entries = %s)", filename, StringUtils.format(alphasSet.size()));
	}
}
