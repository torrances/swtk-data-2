package org.swtk.data.imdb.parse.svc;

import java.io.File;

import org.swtk.data.imdb.json.dmo.ImdbJsonSerializer;
import org.swtk.data.imdb.json.dto.ImdbTitle;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class MinifyJsonForMongodb {

	public static LogManager logger = new LogManager(MinifyJsonForMongodb.class);

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

		String IN = "/Users/craigtrim/Data/data/imdb/json/";
		for (File file : FileUtils.getDescendantFilesInFolder(new File(IN), "json")) {

			String outpath = file.getAbsolutePath();
			outpath = outpath.replaceAll("/json/", "/json-2/");
			outpath = outpath.replaceAll("/all", "");

			ImdbTitle imdbTitle = deserialize(file);
			String output = ImdbJsonSerializer.minify(imdbTitle);
			FileUtils.toFile(output, outpath, Codepage.UTF_8);
		}
	}
}
