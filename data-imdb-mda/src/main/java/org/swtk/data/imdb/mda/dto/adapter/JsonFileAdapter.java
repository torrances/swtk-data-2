package org.swtk.data.imdb.mda.dto.adapter;

import java.io.File;

import org.swtk.data.imdb.mda.dto.JsonFile;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class JsonFileAdapter {

	public static String toString(JsonFile jsonFile) {
		StringBuilder sb = new StringBuilder();

		sb.append("id = " + jsonFile.getId());
		sb.append(", name = " + jsonFile.getName());
		sb.append(", path = " + jsonFile.getPath());

		return sb.toString();
	}

	public static JsonFile transform(File file) throws AdapterValidationException {

		String name = StringUtils.substringBefore(file.getAbsolutePath(), "/all.json");
		name = StringUtils.substringAfterLast(name, "/");

		if (StringUtils.isEmpty(name)) throw new AdapterValidationException("Empty Name (path = %s)", name, file.getAbsolutePath());
		if (!name.contains("tt")) throw new AdapterValidationException("Unexpected Name (name = %s, path = %s)", name, file.getAbsolutePath());

		String id = StringUtils.substringAfter(name, "tt");

		return transform(file.getAbsolutePath(), name, id);
	}

	public static JsonFile transform(String path, String name, String id) throws AdapterValidationException {
		JsonFile jsonFile = new JsonFile();

		jsonFile.setPath(path);
		jsonFile.setName(name);
		jsonFile.setId(id);

		return jsonFile;
	}
}
