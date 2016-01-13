package org.swtk.data.wiktionary.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.swtk.common.dict.dto.wiktionary.Synonym;
import org.swtk.common.dict.dto.wiktionary.adapter.SynonymAdapter;
import org.swtk.common.dict.dto.wiktionary.iter.Synonyms;
import org.swtk.data.wiktionary.parser.dmo.SynonymsParser;

import com.trimc.blogger.commons.exception.BusinessException;

public final class SynonymsParserTest {

	@Test
	public void process() throws BusinessException {
		Synonyms synonyms = new SynonymsParser().transform("=* [[morse]] {{qualifier|obsolete}}* [[sea horse]] {{qualifier|rare}}");
		assertNotNull(synonyms);
		assertEquals(2, synonyms.size());
		
		Synonym[] arr = (Synonym[]) synonyms.getList().toArray(new Synonym[synonyms.getList().size()]);
		assertEquals("text = morse, qualifier = obsolete", SynonymAdapter.toString(arr[0]));
		assertEquals("text = sea horse, qualifier = rare", SynonymAdapter.toString(arr[1]));
	}
}
