package org.swtk.data.wordnet.parse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.swtk.common.dict.dto.wordnet.IndexNoun;
import org.swtk.common.dict.dto.wordnet.adapter.IndexNounAdapter;

public final class IndexNounAdapterTest {

	@Test
	public void transform() throws Throwable {
		String line = "abnormality n 4 4 ! @ ~ + 4 1 14525310 05654380 04805434 00738486       ";
		IndexNoun bean = IndexNounAdapter.transform(line);
		assertEquals("term = abnormality, pos = Noun, synset-count = 4, IDs = 00738486;04805434;05654380;14525310", bean.toString());
	}
}
