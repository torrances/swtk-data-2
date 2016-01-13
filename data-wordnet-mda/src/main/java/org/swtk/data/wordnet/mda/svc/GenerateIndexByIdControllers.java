package org.swtk.data.wordnet.mda.svc;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.common.dict.dto.wordnet.IndexNoun;
import org.swtk.data.wordnet.mda.utils.WordnetMdaConstants;
import org.swtk.data.wordnet.parse.dmo.IndexNounParser;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class GenerateIndexByIdControllers {

	public static LogManager logger = new LogManager(GenerateIndexByIdControllers.class);

	public static Set<String> idSet = new TreeSet<String>();

	public static String JAVA_ADD_PATTERN = "\tif (\"%s\".equals(key)) return WordnetNounIndexIdInstance%s.get(ID);\n";

	public static File TEMPLATE = WordnetMdaConstants.TemplateIndexByIdController;

	public static File SOURCE = WordnetMdaConstants.SrcFileIndexNoun;

	public static void process() throws BusinessException {

		/*	check if essential files exist */
		if (!SOURCE.exists()) throw new BusinessException("Input file does not exist (path = %s)", SOURCE.getAbsolutePath());
		if (!TEMPLATE.exists()) throw new BusinessException("Template file does not exist (path = %s)", TEMPLATE.getAbsolutePath());

		/*	create index of IDs */
		for (String line : FileUtils.toList(SOURCE, Codepage.UTF_8)) {
			IndexNoun bean = new IndexNounParser().process(line);
			for (String id : bean.getIds())
				idSet.add(id.substring(0, 4));
		}

		/*	generate the file */
		StringBuilder sb = new StringBuilder();
		for (String id : idSet)
			sb.append(String.format(JAVA_ADD_PATTERN, id, id));

		String outFileName = WordnetMdaConstants.OutFileIndexByIdController;
		String outPkgName = WordnetMdaConstants.OutPathIndexByIdController;
		String template = FileUtils.toString(TEMPLATE, Codepage.UTF_8);
		template = StringUtils.replace(template, "#content", sb.toString());

		String filename = outPkgName + outFileName;
		FileUtils.toFile(template, filename, Codepage.UTF_8);
		logger.info("Wrote to file (path = %s, total-entries = %s)", filename, StringUtils.format(idSet.size()));
	}
}
