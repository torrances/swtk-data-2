package org.swtk.data.wiktionary.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.swtk.common.dict.dto.wiktionary.iter.Definitions;
import org.swtk.data.wiktionary.parser.dmo.DefinitionsParser;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class DefinitionsParserTest {

	public static LogManager logger = new LogManager(DefinitionsParserTest.class);

	public static final String BASE = "../data-wiktionary-parser/src/test/resources/wiktionary/";

	@Test
	public void test() throws Throwable {
		test("apap.txt", "text = Paracetamol, a particular kind of painkiller and fever-reducer generally available over the counter, priority = 1, pos = Noun");
		test("radioimmunodiffusion.txt", "text = immunodiffusion using antigens or antibodies labelled with radioisotopes, priority = 1, pos = Noun");
		test("tidewrack.txt", "text = seaweed and similar marine vegetation and rubbish deposited along a shore by a receding tide, priority = 1, pos = Noun");
	}

	private void test(String name, String theExpectedText) throws Throwable {

		File in = new File(BASE + name);
		assertTrue(in.exists());

		String contents = FileUtils.toString(in, Codepage.UTF_8);
		assertTrue(StringUtils.hasValue(contents));

		Definitions definitions = new DefinitionsParser().process(contents);
		assertNotNull(definitions);
		assertFalse(definitions.isEmpty());

		logger.info("Test Results:\n\tactual-results = %s\n\texpected-results = %s", definitions.toString(), theExpectedText);
		assertTrue(definitions.toString().contains(theExpectedText));
	}
}
