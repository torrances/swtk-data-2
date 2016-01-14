package org.swtk.data.imdb.mda.dto.adapter;

import org.swtk.data.imdb.json.dto.ImdbTitle;
import org.swtk.data.imdb.mda.dto.InstanceTemplateContract;
import org.swtk.data.imdb.mda.dto.JsonFile;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class InstanceTemplateContractAdapter {

	public static String toString(InstanceTemplateContract contract) {
		StringBuilder sb = new StringBuilder();

		sb.append("class-name: " + contract.getClassName());
		sb.append(", file-name: " + contract.getFilename());
		sb.append(", pkg-suffix: " + contract.getPkgSuffix());
		sb.append(", target-file-path-suffix: " + contract.getTargetFilePathSuffix());
		sb.append(String.format(", content (length = %s)\n\t%s", contract.getContent().length(), contract.getContent()));

		return sb.toString();
	}

	public static InstanceTemplateContract transform(JsonFile jsonFile, ImdbTitle imdbTitle) throws AdapterValidationException {
		
		String content = GsonUtils.toJsonSingleLineAll(imdbTitle);
		content = StringUtils.replace(content, "\\\"", "\"");
		content = StringUtils.replace(content, "\"", "\\\"");

		String className = jsonFile.getName().toUpperCase();
		String filename = className.concat(".java");

		StringBuilder sbPkgSuffix = new StringBuilder();
		StringBuilder sbPathSuffix = new StringBuilder();

		char[] arr = jsonFile.getId().toCharArray();
		for (int i = 0; i < arr.length; i++) {

			sbPkgSuffix.append("n" + arr[i]);
			sbPathSuffix.append("n" + arr[i]);

			if (i < arr.length - 1) {
				sbPkgSuffix.append(".");
				sbPathSuffix.append("/");
			}
		}

		return transform(filename, className, sbPkgSuffix.toString(), sbPathSuffix.toString(), content);
	}

	public static InstanceTemplateContract transform(String filename, String className, String pkgSuffix, String targetFilePathSuffix, String content) throws AdapterValidationException {
		InstanceTemplateContract contract = new InstanceTemplateContract();

		/*	private String content;
		
		private String filename;
		
		private String className;
		
		private String pkgSuffix;
		*/
		contract.setFilename(filename);
		contract.setClassName(className);
		contract.setPkgSuffix(pkgSuffix);
		contract.setContent(content);
		contract.setTargetFilePathSuffix(targetFilePathSuffix);

		return contract;
	}
}
