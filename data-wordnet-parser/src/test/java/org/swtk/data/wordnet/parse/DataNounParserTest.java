package org.swtk.data.wordnet.parse;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.swtk.common.dict.dto.wordnet.DataNoun;
import org.swtk.data.wordnet.parse.dmo.DataNounParser;
public final class DataNounParserTest {

	@Test
	public void run() throws Throwable {
		String line = "02777635 06 n 02 baggage 0 luggage 0 011 @ 02978156 n 0000 + 01456857 v 0201 ~ 02776843 n 0000 ~ 03242542 n 0000 %p 03491080 n 0000 ~ 03492616 n 0000 ~ 03503566 n 0000 ~ 03568791 n 0000 ~ 04144300 n 0000 %p 04340267 n 0000 ~ 04499064 n 0000 | cases used to carry belongings when traveling  ";
		DataNoun dataNoun = new DataNounParser().process(line);
		assertNotNull(dataNoun);
		System.err.println(dataNoun.toString());
	}
}
