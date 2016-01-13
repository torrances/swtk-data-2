package org.swtk.data.wiktionary.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.swtk.data.wiktionary.parser.dmo.EtymologyTextNormalization;

import com.trimc.blogger.commons.LogManager;

public final class EtymologyTextNormalizationTest {

	public static LogManager logger = new LogManager(EtymologyTextNormalizationTest.class);

	@Test
	public void test1() throws Throwable {
		String text = "Compare Icelandic {{term|hross||a horse|lang=is}} and {{term|hvalur||a whale|lang=is}} and German {{term|Walross|lang=de}}";
		test(text, "Compare Icelandic 'hross' (a horse) and 'hvalur' (a whale) and German 'walross'");
	}

	@Test
	public void test2() throws Throwable {
		String text = "From {{etyl|da|en}} {{term|hvalros|lang=da}}, an inversion of {{etyl|non|en}} {{term|hrosshvalr||horse-whale|lang=non}}.";
		test(text, "From Danish 'hvalros' an inversion of Old norse 'hrosshvalr' (horse-whale)");
	}

	private void test(String text, String theExpectedResult) throws Throwable {
		assertNotNull(text);
		assertNotNull(theExpectedResult);

		String theActualResult = new EtymologyTextNormalization().process(text);
		logger.info("Test Result:\n\tinput = %s\n\texpected-result = %s\n\tactual-result = %s", text, theExpectedResult, theActualResult);
		
		assertNotNull(theActualResult);
		assertEquals(theExpectedResult, theActualResult);
	}
}
