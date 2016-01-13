package org.swtk.data.wordnet.mda.utils;

import java.io.File;

import org.swtk.common.framework.type.Alpha;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class IndexByNameControllerHelper {

	public static String getOutFileName(String outFileName, Alpha alpha1, Alpha alpha2) {
		outFileName = StringUtils.replace(outFileName, "#n1", alpha1.upper());
		outFileName = StringUtils.replace(outFileName, "#n2", alpha2.upper());

		return outFileName;
	}

	public static String getTemplateContents(final File TEMPLATE, Alpha alpha1, Alpha alpha2, StringBuilder sbContents01, StringBuilder sbContents02, StringBuilder sbImports) throws BusinessException {
		String template = FileUtils.toString(TEMPLATE, Codepage.UTF_8);

		template = StringUtils.replace(template, "#content1", sbContents01.toString());
		template = StringUtils.replace(template, "#content2", sbContents02.toString());
		template = StringUtils.replace(template, "#imports", sbImports.toString());
		template = StringUtils.replace(template, "#n1", alpha1.upper());
		template = StringUtils.replace(template, "#n2", alpha2.upper());
		template = StringUtils.replace(template, "#p1", alpha1.lower());
		template = StringUtils.replace(template, "#p2", alpha2.lower());

		return template;
	}

	public static String getOutPkgName(String outPkgName, Alpha alpha1, Alpha alpha2) {
		outPkgName = StringUtils.replace(outPkgName, "#n1", alpha1.lower());
		outPkgName = StringUtils.replace(outPkgName, "#n2", alpha2.lower());

		return outPkgName;
	}

	public static String getImportPattern(String importPattern, String term) {

		importPattern = StringUtils.replace(importPattern, "#p1", term.substring(0, 1).toLowerCase());
		importPattern = StringUtils.replace(importPattern, "#n1", term.substring(0, 1).toUpperCase());

		if (term.length() >= 2) {
			importPattern = StringUtils.replace(importPattern, "#p2", term.substring(1, 2).toLowerCase());
			importPattern = StringUtils.replace(importPattern, "#n2", term.substring(1, 2).toUpperCase());
		} else {
			importPattern = StringUtils.replace(importPattern, "#.p2", StringUtils.EMPTY);
			importPattern = StringUtils.replace(importPattern, "#n2", StringUtils.EMPTY);
		}

		if (term.length() >= 3) {
			importPattern = StringUtils.replace(importPattern, "#p3", term.substring(2, 3).toLowerCase());
			importPattern = StringUtils.replace(importPattern, "#n3", term.substring(2, 3).toUpperCase());
		} else {
			importPattern = StringUtils.replace(importPattern, ".#p3", StringUtils.EMPTY);
			importPattern = StringUtils.replace(importPattern, "#n3", StringUtils.EMPTY);
		}

		/*if (term.length() >= 4) {
			importPattern = StringUtils.replace(importPattern, "#p4", term.substring(3, 4).toLowerCase());
			importPattern = StringUtils.replace(importPattern, "#n4", term.substring(3, 4).toUpperCase());
		} else {
			importPattern = StringUtils.replace(importPattern, ".#p4", StringUtils.EMPTY);
			importPattern = StringUtils.replace(importPattern, "#n4", StringUtils.EMPTY);
		}*/

		return importPattern;
	}
}
