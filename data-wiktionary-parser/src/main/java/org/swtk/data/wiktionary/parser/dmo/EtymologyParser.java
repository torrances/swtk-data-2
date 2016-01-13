package org.swtk.data.wiktionary.parser.dmo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.swtk.common.dict.dto.wiktionary.Etymology;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class EtymologyParser {

	public static LogManager logger = new LogManager(EtymologyParser.class);

	public Map<String, String> languageCodesNotFound = new TreeMap<String, String>();

	public Map<String, String> synonymQualifiersNotFound = new TreeMap<String, String>();

	private String getContent(String contents) {
		String text = StringUtils.substringAfter(contents, "===Etymology===");
		text = StringUtils.substringBefore(text, "===");
		text = text.replaceAll(System.lineSeparator(), " ");
		text = StringUtils.trim(text);

		if (text.contains("</")) {
			text = StringUtils.substringBefore(text, "</");
		}

		text = StringUtils.toString(normalizeWikiMarkup(text), ". ");
		return text;
	}

	private Collection<String> normalizeWikiMarkup(String text) {
		List<String> list = new ArrayList<String>();

		String normalized = text;
		normalized = StringUtils.trim(normalized);

		normalized = StringUtils.replace(normalized, "[[", "");
		normalized = StringUtils.replace(normalized, "]]", "");
		normalized = StringUtils.replace(normalized, "''", "'");
		normalized = StringUtils.replace(normalized, "\\\\", " ");
		normalized = StringUtils.replace(normalized, "\\", " ");

		for (String line : normalized.split(".#"))
			list.add(StringUtils.trim(line));

		return list;
	}

	public Etymology process(String contents) throws BusinessException {
		Etymology bean = new Etymology();

		String text = getContent(contents);

		EtymologyTextNormalization etn = new EtymologyTextNormalization();
		EtymologyLanguageExtractor eln = new EtymologyLanguageExtractor();

		bean.setLanguages(eln.process(text).values());
		bean.setInfluencers(new TermInfluenceExtractor().process(text));
		bean.setText(etn.process(text));

		languageCodesNotFound.putAll(etn.languageCodesNotFound);
		languageCodesNotFound.putAll(eln.languageCodesNotFound);
		synonymQualifiersNotFound.putAll(etn.synonymQualifiersNotFound);
		synonymQualifiersNotFound.putAll(eln.synonymQualifiersNotFound);

		return bean;
	}
}
