package org.swtk.data.twitter.gnip.parse;

import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.codec.language.ColognePhonetic;
import org.swtk.eng.tokenizer.text.TextTokenizer;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.SetUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class MinHashTest2 {

	public static LogManager logger = new LogManager(MinHashTest2.class);

	public static void main(String... args) throws Throwable {

		String t1 = "foxnews glennbeck tedcruz realdonaldtrump gopdebate trump every candidate has their marks including cruz why the emotional hate";
		String t2 = "the quick brown fox jumps over the lazy dog";

		Set<String> dm1 = toDoubleMetaphone(tokenizeToSet(t1));
		Set<String> dm2 = toDoubleMetaphone(tokenizeToSet(t2));

		logger.info("\n\tdm-1: %s", StringUtils.toString(dm1, ", "));
		logger.info("\n\tdm-2: %s", StringUtils.toString(dm2, ", "));

		double s = new MinHash<String>(20).similarity(dm2, dm1);
		System.err.println(s);

	}

	public static Set<String> toDoubleMetaphone(Set<String> tokens) {
		Set<String> set = new TreeSet<String>();

		for (String token : tokens)
			set.add(new ColognePhonetic().encode(token));

		return set;
	}

	public static Set<String> tokenizeToSet(String input) throws BusinessException {
		return SetUtils.toSet(new TextTokenizer(input).tokenize().array());
	}
}
