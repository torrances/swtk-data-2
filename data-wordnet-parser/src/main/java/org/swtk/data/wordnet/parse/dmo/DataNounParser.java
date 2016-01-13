package org.swtk.data.wordnet.parse.dmo;

import java.util.Set;
import java.util.TreeSet;

import org.swtk.common.dict.dto.wordnet.DataNoun;
import org.swtk.common.dict.dto.wordnet.adapter.DataNounAdapter;
import org.swtk.common.dict.utils.WordnetAdapterUtils;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class DataNounParser {

	public static LogManager logger = new LogManager(DataNounParser.class);

	public DataNoun process(String line) throws BusinessException {

		// if (!line.contains("@")) throw new AdapterValidationException("Unexpected line format (reason = no hypernym)\n\tline: %s: ", line);

		DataNoun dataNoun = new DataNoun();
		String delimiter = (line.contains("@")) ? "@" : "~";

		dataNoun.setDataNounFrames(new DataNounFrameParser().process(line, delimiter));

		String temp = StringUtils.substringBefore(line, delimiter).trim();
		String[] tokens = temp.split(" ");

		// "02777635 06 n 02 baggage 0 luggage 0 011 @ 
		dataNoun.setId(DataNounAdapter.transformId(tokens[0]));
		dataNoun.setUpperType(WordnetAdapterUtils.transformPos(tokens[2]));
		dataNoun.setTerm(DataNounAdapter.transformTerm(tokens[4]));

		Set<String> set = new TreeSet<String>();
		for (int i = 6; i < tokens.length; i++)
			if (TextUtils.isAlphaOnly(tokens[i])) set.add(tokens[i]);
		dataNoun.setSynonyms(set);

		return dataNoun;
	}
}
