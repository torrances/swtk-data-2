package org.swtk.data.wiktionary.parser.dmo;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.swtk.common.dict.dto.wiktionary.TermInfluence;
import org.swtk.common.dict.dto.wiktionary.adapter.TermInfluenceAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public class TermInfluenceExtractor {

	public static LogManager logger = new LogManager(TermInfluenceExtractor.class);

	/**		sample pattern:
	 * 		Compare Icelandic {{term|hross||a horse|lang=is}} and {{term|hvalur||a whale|lang=is}} and German {{term|Walross|lang=de}}	
	 */
	private static final Pattern pattern = Pattern.compile("\\{\\{term\\|([a-z\\s]+)\\|\\|([a-z\\s]+)\\|lang=([a-z]+)\\}\\}");

	public Collection<TermInfluence> process(String text) throws BusinessException {
		Map<Integer, TermInfluence> map = new TreeMap<Integer, TermInfluence>();

		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			
			String m1 = matcher.group(1);
			String m2 = matcher.group(2);
			String m3 = matcher.group(3);
			
			logger.trace("Found Match (m1 = %s, m2 = %s, m3 = %s)", m1, m2, m3);
			TermInfluence bean = TermInfluenceAdapter.tranform(m1, m2, m3);
			map.put(bean.hashCode(), bean);
		}

		return map.values();
	}
}
