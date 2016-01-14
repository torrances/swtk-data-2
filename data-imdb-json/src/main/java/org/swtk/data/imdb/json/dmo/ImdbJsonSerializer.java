package org.swtk.data.imdb.json.dmo;

import org.swtk.data.imdb.json.dto.ImdbTitle;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.utils.GsonUtils;

public class ImdbJsonSerializer {

	private static ImdbJsonSerializer instance;

	public static LogManager logger = new LogManager(ImdbJsonSerializer.class);

	public static ImdbJsonSerializer getInstance() {
		if (null == instance)
			instance = new ImdbJsonSerializer();

		return instance;
	}

	public static String minify(ImdbTitle imdbTitle) {
		return GsonUtils.toJsonSingleLineAll(imdbTitle);
	}

	public static String pretty(ImdbTitle imdbTitle) {
		return GsonUtils.toJsonFormatted(imdbTitle);
	}

	public static ImdbTitle toObj(String imdbTitle) {
		return GsonUtils.toObject(imdbTitle, ImdbTitle.class);
	}

	private ImdbJsonSerializer() {
	}
}
