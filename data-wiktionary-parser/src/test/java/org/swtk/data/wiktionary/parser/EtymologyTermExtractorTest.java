package org.swtk.data.wiktionary.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.swtk.common.dict.dto.wiktionary.TermInfluence;
import org.swtk.data.wiktionary.parser.dmo.TermInfluenceExtractor;

import com.trimc.blogger.commons.LogManager;

public final class EtymologyTermExtractorTest {

	public static LogManager logger = new LogManager(EtymologyTermExtractorTest.class);

	private void test(String text, String theExpectedResult) throws Throwable {
		Collection<TermInfluence> beans = new TermInfluenceExtractor().process(text);
		assertNotNull(beans);

		StringBuilder sb = new StringBuilder();
		Iterator<TermInfluence> iter = beans.iterator();
		while (iter.hasNext()) {
			sb.append(iter.next().toString());
			if (iter.hasNext()) sb.append("; ");
		}

		logger.info("Test Results:\n\tinput = %s\n\texpected-result = %s\n\tactual-result = %s", text, theExpectedResult, sb.toString());
		assertEquals(theExpectedResult, sb.toString());
	}

	@Test
	public void test1() throws Throwable {
		test("Compare Icelandic {{term|hross||a horse|lang=is}} and {{term|hvalur||a whale|lang=is}} and German {{term|Walross|lang=de}}", "a whale, foreign-term = hvalur; a horse, foreign-term = hross");
	}
}
