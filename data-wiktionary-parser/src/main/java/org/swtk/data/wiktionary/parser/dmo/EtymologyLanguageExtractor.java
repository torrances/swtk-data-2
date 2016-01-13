package org.swtk.data.wiktionary.parser.dmo;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.swtk.common.dict.iso639.dmo.Iso639Db;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.map.MapUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class EtymologyLanguageExtractor {

	public Map<String, String> languageCodesNotFound = new TreeMap<String, String>();

	public Map<String, String> synonymQualifiersNotFound = new TreeMap<String, String>();

	public LogManager logger = new LogManager(EtymologyLanguageExtractor.class);

	private void extract(Set<String> set, String text) {

		String temp = StringUtils.substringAfter(text, "{{etyl|");
		temp = StringUtils.substringBefore(temp, "}}");

		for (String candidate : temp.split("\\|"))
			if (StringUtils.hasValue(candidate) && TextUtils.isAlphaOnly(candidate)) set.add(candidate);

		temp = "{{etyl|" + temp + "}}";
		if (text.contains("{{etyl")) {

			String _text = StringUtils.replace(text, temp, "");
			if (!text.equals(_text)) extract(set, _text);
		}
	}

	private Map<String, String> lookup(Collection<String> collection) throws BusinessException {
		Map<String, String> map = new TreeMap<String, String>();

		for (String extractedKey : collection) {

			if (!Iso639Db.hasById(extractedKey)) MapUtils.add2Map(languageCodesNotFound, extractedKey);
			else {
				String language = Iso639Db.findById(extractedKey).getName();
				if (!StringUtils.hasValue(language)) continue;
				map.put(extractedKey, language);
			}
		}

		return map;
	}

	public Map<String, String> process(String text) throws BusinessException {
		Set<String> set = new TreeSet<String>();
		extract(set, text);
		return lookup(set);
	}
}
