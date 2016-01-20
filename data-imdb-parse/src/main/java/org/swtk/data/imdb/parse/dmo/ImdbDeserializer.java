package org.swtk.data.imdb.parse.dmo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.swtk.data.imdb.json.dto.ImdbTitle;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class ImdbDeserializer {

	public static LogManager logger = new LogManager(ImdbDeserializer.class);

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

	public static Collection<ImdbTitle> process(String path) throws Throwable {
		Collection<ImdbTitle> list = new ArrayList<ImdbTitle>();
		for (File file : FileUtils.getDescendantFilesInFolder(new File(path), "json"))
			list.add(deserialize(file));

		logger.debug("Loaded Files (total = %s)", StringUtils.format(list.size()));
		return list;
	}
}
