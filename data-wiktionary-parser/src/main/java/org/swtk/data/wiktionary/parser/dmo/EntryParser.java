package org.swtk.data.wiktionary.parser.dmo;

import java.util.Map;
import java.util.TreeMap;

import org.swtk.common.dict.dto.wiktionary.Entry;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class EntryParser {

	public static LogManager logger = new LogManager(EntryParser.class);

	public Map<String, String> languageCodesNotFound = new TreeMap<String, String>();

	public Map<String, String> synonymQualifiersNotFound = new TreeMap<String, String>();

	public Entry process(String contents) throws BusinessException {
		Entry entry = new Entry();

		EtymologyParser etymologyParser = new EtymologyParser();
		entry.setEtymology(etymologyParser.process(contents));
		entry.setSynonyms(new SynonymsParser().process(contents));
		entry.setDefinitions(new DefinitionsParser().process(contents));

		String term = StringUtils.substringAfter(contents, "<title>");
		term = StringUtils.substringBefore(term, "</title>");
		entry.setTerm(StringUtils.trim(term).toLowerCase());

		languageCodesNotFound.putAll(etymologyParser.languageCodesNotFound);
		synonymQualifiersNotFound.putAll(etymologyParser.synonymQualifiersNotFound);
		
		return entry;
	}
}
