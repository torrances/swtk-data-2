package org.swtk.data.imdb.mda.svc;

import java.io.File;

import org.swtk.data.imdb.json.dto.ImdbTitle;
import org.swtk.data.imdb.mda.dto.InstanceTemplateContract;
import org.swtk.data.imdb.mda.dto.JsonFile;
import org.swtk.data.imdb.mda.dto.adapter.InstanceTemplateContractAdapter;
import org.swtk.data.imdb.mda.dto.adapter.JsonFileAdapter;
import org.swtk.data.imdb.mda.utils.Constants;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

// TODO: Spring-ify
public class GenerateClasses {

	public static LogManager logger = new LogManager(GenerateClasses.class);

	public static final Integer THRESHOLD = 50000;

	private static ImdbTitle deserialize(File file) throws BusinessException {
		String json = FileUtils.toString(file, Codepage.UTF_8);
		if (StringUtils.isEmpty(json)) return null;

		try {
			return GsonUtils.toObject(json, ImdbTitle.class);
		} catch (Exception e) {
			logger.error(e);
			throw new BusinessException("Unable to deserialize file (path = %s)", file.getAbsolutePath());
		}
	}

	public static void main(String... args) throws Throwable {

		int counter = 0;
		File templatePath = new File(Constants.TEMPLATE);

		File dir = new File("/Users/craigtrim/data/Data/imdb/json/");
		for (File file : FileUtils.getDescendantFilesInFolder(dir.getAbsolutePath(), "json")) {

			if (++counter > 5000) return;

			// 1000=5096, 5000=2	7386

			try {

				ImdbTitle imdbTitle = deserialize(file);
				if (null == imdbTitle) continue;

				JsonFile jsonFile = JsonFileAdapter.transform(file);
				InstanceTemplateContract contract = InstanceTemplateContractAdapter.transform(jsonFile, imdbTitle);

				String srcFilePath = Constants.TARGET_DIR_JAVA + contract.getTargetFilePathSuffix() + "/" + contract.getFilename();
				if (new File(srcFilePath).exists()) continue;

				String template = FileUtils.toString(templatePath, Codepage.UTF_8);
				template = StringUtils.replace(template, "#EOL", System.lineSeparator());
				template = StringUtils.replace(template, "#className", contract.getClassName());
				template = StringUtils.replace(template, "#pkgSuffix", contract.getPkgSuffix());

				/* 	how about a combination of files and classes? 
				 * 	if string data exceeds a length, then embed a path to the file system */

				template = StringUtils.replace(template, "#id", imdbTitle.getId());
				template = StringUtils.replace(template, "#castMembers", toJson("castMembers", contract, imdbTitle.getCastMembers()));
				template = StringUtils.replace(template, "#faqEntries", toJson("faqEntries", contract, imdbTitle.getFaqEntries()));
				template = StringUtils.replace(template, "#keywords", toJson("keywords", contract, imdbTitle.getKeywords()));
				template = StringUtils.replace(template, "#movies", toJson("movies", contract, imdbTitle.getMovies()));
				template = StringUtils.replace(template, "#plotSummaries", toJson("plotSummaries", contract, imdbTitle.getPlotSummaries()));
				template = StringUtils.replace(template, "#quotations", toJson("quotations", contract, imdbTitle.getQuotations()));
				template = StringUtils.replace(template, "#synopses", toJson("synopses", contract, imdbTitle.getSynopses()));
				template = StringUtils.replace(template, "#tagLines", toJson("tagLines", contract, imdbTitle.getTaglines()));
				template = StringUtils.replace(template, "#userRatings", toJson("userRatings", contract, imdbTitle.getUserRatings()));

				FileUtils.toFile(template, srcFilePath, Codepage.UTF_8);

			} catch (AdapterValidationException e) {
				logger.error(e);
				continue;
			}
		}
	}

	private static String toJson(String type, InstanceTemplateContract contract, Object obj) throws BusinessException {
		String content = GsonUtils.toJsonSingleLineAll(obj);

		if (content.length() > THRESHOLD) {
			String txtFilePath = Constants.TARGET_DIR_TEXT + contract.getTargetFilePathSuffix() + "/" + type + ".txt";

			File txtFile = new File(txtFilePath);
			if (!txtFile.exists()) FileUtils.toFile(content, txtFile, Codepage.UTF_8);

			StringBuilder sb = new StringBuilder();
			sb.append("JsonFileLoader.");
			sb.append(type);
			sb.append("(");
			sb.append(txtFile.getAbsolutePath());
			sb.append(");");

			return sb.toString();
		}

		content = StringUtils.replace(content, "\\\"", "\"");
		content = StringUtils.replace(content, "\"", "\\\"");

		return content;
	}
}
