package org.swtk.data.wordnet.parse.dmo;

import java.util.Arrays;

import org.swtk.common.dict.dto.wordnet.DataNounFrames;
import org.swtk.common.dict.dto.wordnet.adapter.DataNounFrameAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class DataNounFrameParser {

	public static LogManager logger = new LogManager(DataNounFrameParser.class);

	public DataNounFrames process(String line, String delimiter) throws BusinessException {

		// if (!line.contains("@")) throw new AdapterValidationException("Unexpected line format (reason = no hypernym)\n\tline: ", line);
		if (!line.contains("|")) throw new AdapterValidationException("Unexpected line format (reason = no gloss)\n\tline: ", line);

		String temp = delimiter + " " + StringUtils.substringAfter(line, delimiter).trim();
		temp = StringUtils.substringBefore(temp, "|").trim();

		/*	specialized case ... */
		temp = StringUtils.replace(temp, "@ i ", "@i ");

		String tokens[] = temp.split(" ");
		if (0 != tokens.length % 4) throw new AdapterValidationException("Unexpected line format (reason = cluster length, total-tokens = %s)\n\ttokens = %s\n\tline = %s: ", tokens.length, StringUtils.toString(Arrays.asList(tokens), " "), line);

		DataNounFrames dataNounFrames = new DataNounFrames();
		for (int i = 0; i < tokens.length; i += 4) {
			String frameType = tokens[i];
			String id = tokens[i + 1];
			String pos = tokens[i + 2];
			String semPointer = tokens[i + 3];

			try {
				dataNounFrames.add(DataNounFrameAdapter.transform(frameType, id, pos, semPointer));
			} catch (AdapterValidationException e) {
				throw new BusinessException("Failed to create data noun frame (delimiter = %s):\n\tline = %s", delimiter, line);
			}
		}

		logger.trace("Wordnet Frames extracted (total = %s):\n\tline = %s", dataNounFrames.size(), line);
		return dataNounFrames;
	}
}
