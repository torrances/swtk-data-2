package org.swtk.data.wiktionary.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.swtk.data.wiktionary.parser.dmo.EtymologyLanguageExtractor;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.utils.map.MapUtils;

public final class EtymologyLanguageExtractorTest {

	public static LogManager logger = new LogManager(EtymologyLanguageExtractorTest.class);

	@Test
	public void test1() throws Throwable {
		test("From {{etyl|da|en}} {{term|hvalros|lang=da}}, an inversion of {{etyl|non|en}} {{term|hrosshvalr||horse-whale|lang=non}}.", "da, Danish; en, English; non, old norse");
		test("The term may have entered English via {{etyl|nl|en}} {{term|walrus|lang=nl}}.", "en, English; nl, Dutch");

		// TODO: these are comparisons:
		// test("Compare Icelandic {{term|hross||a horse|lang=is}} and {{term|hvalur||a whale|lang=is}} and German {{term|Walross|lang=de}}.", "de, german; is, icelandic");
	}

	private void test(String text, String theExpectedResults) throws Throwable {
		Map<String, String> languages = new EtymologyLanguageExtractor().process(text);

		assertNotNull(languages);
		String theActualResults = MapUtils.toString1(languages, "; ");

		logger.info("Test Results\n\tinput = %s\n\texpected-results = %s\n\tactual-results = %s", text, theExpectedResults, theActualResults);
		assertEquals(theExpectedResults, theActualResults);
	}
}
