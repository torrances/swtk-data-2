package org.swtk.data.wiktionary.parser.dmo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.swtk.common.dict.dto.wiktionary.adapter.SynonymAdapter;
import org.swtk.common.dict.dto.wiktionary.iter.Synonyms;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.SetUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class SynonymsParser {

	public static LogManager logger = new LogManager(SynonymsParser.class);

	/**		sample pattern:
	 * 		=* [[morse]] {{qualifier|obsolete}}* [[sea horse]] {{qualifier|rare}}	
	 */
	private static final Pattern pattern = Pattern.compile("(\\[\\[[a-z\\s]+\\]\\])[\\s](\\{\\{qualifier\\|[a-z]+\\}\\})");

	public Synonyms process(String contents) throws BusinessException {
		String text = StringUtils.substringAfter(contents, "===Synonyms===");

		text = StringUtils.substringBefore(text, "==");
		text = text.replaceAll(System.lineSeparator(), " ");
		text = StringUtils.trim(text);

		return transform(text);
	}

	public Synonyms transform(String text) throws BusinessException {
		Synonyms synonyms = new Synonyms();

		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {

			String term = matcher.group(1);
			term = term.replaceAll("\\[", "").replaceAll("\\]", "");

			String qualifier = matcher.group(2);
			qualifier = qualifier.replaceAll("\\{", "").replaceAll("\\}", "");
			qualifier = StringUtils.substringAfter(qualifier, "|").trim();

			String[] blacklist = { "generally", "indistinctly", "religious", "mahogany" };
			try {
				if (!SetUtils.memberOf(qualifier, blacklist)) synonyms.add(SynonymAdapter.transform(term, qualifier));
			} catch (AdapterValidationException e) {
				logger.error("No Synonyms Qualifier Extracted: %s", text);
			}
		}

		return synonyms;
	}
}
