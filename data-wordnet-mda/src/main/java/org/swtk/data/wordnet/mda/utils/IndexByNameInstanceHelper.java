package org.swtk.data.wordnet.mda.utils;

import java.io.File;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class IndexByNameInstanceHelper {

	public static LogManager logger = new LogManager(IndexByNameInstanceHelper.class);

	public static String getOutFilename(String value, final String key) throws BusinessException {
		value = StringUtils.replace(value, "#n1", key.substring(0, 1).toUpperCase());

		if (key.length() >= 2) {
			value = StringUtils.replace(value, "#n2", key.substring(1, 2).toUpperCase());
		} else {
			value = StringUtils.replace(value, "#n2", "");
		}

		if (key.length() >= 3) {
			value = StringUtils.replace(value, "#n3", key.substring(2, 3).toUpperCase());
		} else {
			value = StringUtils.replace(value, "#n3", "");
		}

		/*if (key.length() >= 4) {
			value = StringUtils.replace(value, "#n4", key.substring(3, 4).toUpperCase());
		} else {
			value = StringUtils.replace(value, "#n4", "");
		}*/

		logger.trace("Generated Output File Name (key = %s, value = %s)", key, value);
		return value;
	}

	public static String getOutPackageName(String value, final String key) throws BusinessException {
		value = StringUtils.replace(value, "#n1", key.substring(0, 1).toLowerCase());
		value = StringUtils.replace(value, "#n2", key.substring(1, 2).toLowerCase());

		if (key.length() >= 3) {
			value = StringUtils.replace(value, "#n3", key.substring(2, 3).toLowerCase());
		} else {
			value = StringUtils.replace(value, "#n3", "");
		}

		/*if (key.length() >= 4) {
			value = StringUtils.replace(value, "#n4", key.substring(3, 4).toLowerCase());
		} else {
			value = StringUtils.replace(value, "#n4", "");
		}*/

		logger.trace("Generated Output Package Name (key = %s, value = %s)", key, value);
		return value;
	}

	public static String getTemplateClassName(final File TEMPLATE, String key) throws BusinessException {
		String template = FileUtils.toString(TEMPLATE, Codepage.UTF_8);
		template = StringUtils.replace(template, "#n1", key.substring(0, 1).toUpperCase());
		template = StringUtils.replace(template, "#p1", key.substring(0, 1).toLowerCase());

		if (key.length() >= 2) {
			template = StringUtils.replace(template, "#n2", key.substring(1, 2).toUpperCase());
			template = StringUtils.replace(template, "#p2", key.substring(1, 2).toLowerCase());
		} else {
			template = StringUtils.replace(template, "#n2", "");
			template = StringUtils.replace(template, ".#p2", "");
		}

		if (key.length() >= 3) {
			template = StringUtils.replace(template, "#n3", key.substring(2, 3).toUpperCase());
			template = StringUtils.replace(template, "#p3", key.substring(2, 3).toLowerCase());
		} else {
			template = StringUtils.replace(template, "#n3", "");
			template = StringUtils.replace(template, ".#p3", "");
		}

		/*if (key.length() >= 4) {
			template = StringUtils.replace(template, "#n4", key.substring(3, 4).toUpperCase());
			template = StringUtils.replace(template, "#p4", key.substring(3, 4).toLowerCase());
		} else {
			template = StringUtils.replace(template, "#n4", "");
			template = StringUtils.replace(template, ".#p4", "");
		}*/

		logger.trace("Generated Template (key = %s, value = %s)", key, template);
		return template;
	}
}
