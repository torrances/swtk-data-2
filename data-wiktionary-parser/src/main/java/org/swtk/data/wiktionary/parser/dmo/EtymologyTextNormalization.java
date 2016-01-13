package org.swtk.data.wiktionary.parser.dmo;

import java.util.Map;
import java.util.TreeMap;

import org.swtk.common.dict.iso639.dmo.Iso639Db;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.map.MapUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class EtymologyTextNormalization {

	public static LogManager logger = new LogManager(EtymologyTextNormalization.class);

	public Map<String, String> languageCodesNotFound = new TreeMap<String, String>();

	public Map<String, String> synonymQualifiersNotFound = new TreeMap<String, String>();

	/**
	 * Purpose:
	 * Modify
	 * 		Compare Icelandic {{term|hross||a horse|lang=is}} and {{term|hvalur||a whale|lang=is}} and German {{term|Walross|lang=de}}
	 * to
	 * 		Compare Icelandic 'hross' (a horse) and 'hvalur' (a whale) and German 'walross'
	 * @param text
	 * @return {@link String}
	 */
	private String normalizeComparisons(String text) {

		while (text.contains("{{term|")) {
			int x = text.indexOf("{{term|");

			String temp = StringUtils.substringAfter(text, "{{term|");
			temp = StringUtils.substringBefore(temp, "}}");
			int y = x + temp.length() + 9;

			String sub = text.substring(x, y);

			StringBuilder terms = new StringBuilder();
			if (sub.contains("||")) {
				String t1 = StringUtils.substringBefore(StringUtils.substringAfter(sub, "term|"), "||");
				String t2 = StringUtils.substringBefore(StringUtils.substringAfter(sub, "||"), "|");
				terms.append("'" + t1 + "' (" + t2 + ") ");
			} else {
				String t1 = StringUtils.substringBefore(StringUtils.substringAfter(sub, "term|"), "|");
				terms.append("'" + t1 + "' ");
			}

			StringBuilder sb = new StringBuilder();
			sb.append(text.substring(0, x));
			sb.append(terms.toString().toLowerCase());
			if (y + 1 < text.length()) sb.append(text.substring(y + 1, text.length()));
			text = sb.toString();
		}

		return text;
	}

	private String normalizeEtymology(String text) throws BusinessException {

		while (text.contains("{{etyl|")) {

			int x = text.indexOf("{{etyl|");
			String temp = StringUtils.substringAfter(text, "{{etyl|");

			temp = StringUtils.substringBefore(temp, "}}");
			int y = x + temp.length() + 9;

			String sub = text.substring(x, y);
			StringBuilder terms = new StringBuilder();

			if (sub.contains("|")) {
				String t1 = StringUtils.substringBefore(StringUtils.substringAfter(sub, "etyl|"), "|");

				if (!Iso639Db.hasById(t1)) MapUtils.add2Map(languageCodesNotFound, t1);
				else {
					String language = Iso639Db.findById(t1).getName();
					language = language.substring(0, 1).toUpperCase() + language.substring(1).toLowerCase();
					terms.append(language + " ");
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append(text.substring(0, x));
			sb.append(terms.toString());
			if (y + 1 < text.length()) sb.append(text.substring(y + 1, text.length()));
			text = sb.toString();
		}

		return text;
	}

	public String process(String text) throws BusinessException {
		text = normalizeComparisons(text);
		text = normalizeEtymology(text);

		return StringUtils.trim(text);
	}
}
