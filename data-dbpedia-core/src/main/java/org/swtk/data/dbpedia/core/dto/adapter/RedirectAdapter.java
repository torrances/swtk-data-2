package org.swtk.data.dbpedia.core.dto.adapter;

import org.swtk.data.dbpedia.core.dto.Redirect;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class RedirectAdapter {

	private static String cleanse(String value) {

		value = StringUtils.substringBeforeLast(value, ">");
		value = StringUtils.substringAfter(value, "resource/");
		value = StringUtils.replace(value, "_", " ");
		value = value.replaceAll("\\(.*\\)", "");
		value = value.replaceAll("\"", "");

		try {
			/*Y%C5%ABko Mochizuki
Perast manuscript
Julije Balovi%C4%87
*
*/
			System.err.println(value);
			value = java.net.URLDecoder.decode(value, "UTF-8");
		} catch (Exception e) {}

		return value;
	}

	public static String toString(Redirect redirect) {
		StringBuilder sb = new StringBuilder();

		sb.append(redirect.getSubject());
		sb.append(", ");
		sb.append(redirect.getObject());

		return sb.toString().toLowerCase();
	}

	public static Redirect transform(String subject, String object) throws AdapterValidationException {
		Redirect redirect = new Redirect();

		redirect.setSubject(cleanse(subject));
		redirect.setObject(cleanse(object));

		return redirect;
	}
}
