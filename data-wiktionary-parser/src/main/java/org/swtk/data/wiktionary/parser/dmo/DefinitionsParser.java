package org.swtk.data.wiktionary.parser.dmo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.swtk.common.dict.dto.wiktionary.adapter.DefinitionAdapter;
import org.swtk.common.dict.dto.wiktionary.iter.Definitions;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.EngGrammarUpperType;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class DefinitionsParser {

	public static LogManager logger = new LogManager(DefinitionsParser.class);

	private Collection<String> normalizeWikiMarkup(String text) {
		List<String> list = new ArrayList<String>();

		String normalized = text;
		normalized = StringUtils.trim(normalized);

		normalized = StringUtils.replace(normalized, "[[", "");
		normalized = StringUtils.replace(normalized, "]]", "");
		normalized = StringUtils.replace(normalized, "''", "'");
		normalized = normalized.replaceAll("\\{\\{.+?\\}\\}", "");
		normalized = StringUtils.replace(normalized, "\\\\", " ");
		normalized = StringUtils.replace(normalized, "\\", " ");

		for (String line : normalized.split(".#"))
			list.add(StringUtils.trim(line));

		return list;
	}

	private Collection<String> postProcess(Collection<String> lines) {
		List<String> list = new ArrayList<String>();

		for (String line : lines) {

			/*	ignore smaller lines 								*/
			if (line.length() < 5) continue;

			/*	remove leading punctuation 							*/
			while (TextUtils.startsWithPunctuation(line))
				line = line.substring(1, line.length());

			line = line.replaceAll(", ,", ",");

			/*	tokenize line for focused pattern post-processing 	*/
			StringBuilder sb = new StringBuilder();
			for (String token : line.split(" ")) {
				if (token.contains("|")) token = StringUtils.substringAfter(token, "|").trim();
				sb.append(token + " ");
			}

			/*	ignore smaller lines 								*/
			String postProcessed = StringUtils.trim(sb.toString());
			while (TextUtils.endsWithPunctuation(postProcessed))
				postProcessed = postProcessed.substring(0, postProcessed.length() - 1);
			if (postProcessed.length() < 5) continue;

			list.add(postProcessed);
		}

		return list;

	}

	public Definitions process(String contents) throws BusinessException {
		Definitions definitions = new Definitions();

		String text = contents;
		if (text.contains("lang=en}}===Noun===")) {
			text = StringUtils.substringAfter(contents, "lang=en}}===Noun===");
		} else if (text.contains("lang=en}}===Proper noun===")) {
			text = StringUtils.substringAfter(contents, "lang=en}}===Proper noun===");
		} else if (text.contains("===Noun==={{en-noun|-}}")) {
			text = StringUtils.substringAfter(contents, "===Noun==={{en-noun|-}}");
		} else return null;

		text = StringUtils.substringBefore(text, "==");
		if (text.contains("</")) text = StringUtils.substringBefore(text, "</").trim();
		
		Collection<String> list = postProcess(normalizeWikiMarkup(text));

		int priority = 0;
		for (String item : list) {
			definitions.add(DefinitionAdapter.transform(item, EngGrammarUpperType.NOUN, ++priority));
		}

		return definitions;
	}
}
