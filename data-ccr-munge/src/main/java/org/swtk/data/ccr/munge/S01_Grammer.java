package org.swtk.data.ccr.munge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.swtk.common.core.CommonsIocContainer;
import org.swtk.common.dict.beans.NoisyWordsOilandGasDictionary;
import org.swtk.common.dict.beans.NumbersDictionary;
import org.swtk.common.dict.beans.StopWordsDictionary;
import org.swtk.eng.tense.svc.EngLemmatizer;
import org.swtk.eng.tokenizer.text.TextTokenizer;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.map.MapUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class S01_Grammer {

	public static LogManager logger = new LogManager(S01_Grammer.class);

	public static void main(String... args) throws Throwable {

		StopWordsDictionary stopWordsDict = (StopWordsDictionary) CommonsIocContainer.getContext()
				.getBean(StopWordsDictionary.class);

		NumbersDictionary numbersDict = (NumbersDictionary) CommonsIocContainer.getContext()
				.getBean(NumbersDictionary.class);

		NoisyWordsOilandGasDictionary noiseDict = (NoisyWordsOilandGasDictionary) CommonsIocContainer.getContext()
				.getBean(NoisyWordsOilandGasDictionary.class);

		Map<String, String> map = new TreeMap<String, String>();
		for (File file : FileUtils.getDescendantFilesInFolder("/Users/craigtrim/data/Data/ong/03/", "*")) {
			if (!"txt".equals(FileUtils.getExtension(file)))
				continue;

			System.err.println("processing file (" + file.getName() + ") with map (" + map.size() + ")");
			for (String line : FileUtils.toList(file, Codepage.UTF_16LE)) {
				String[] arr = new TextTokenizer(line.toLowerCase()).tokenize().array();

				List<String> list = new ArrayList<String>();
				for (int i = 0; i < arr.length; i++) {

					boolean isStopWord = (stopWordsDict.entries().contains(arr[i]));
					boolean isNumeric = TextUtils.containsNumeric(arr[i]);
					boolean isPunctuation = TextUtils.isPunctuation(arr[i]);
					boolean isShort = arr[i].length() < 2;
					boolean isNumerical = (numbersDict.entries().contains(arr[i]));
					boolean isNoisy = noiseDict.entries().contains(arr[i]);
					boolean invalid = (isStopWord || isNumeric || isPunctuation || isShort || isNumerical || isNoisy);

					if (invalid) {
						if (list.size() > 1) {
							
							String grams = StringUtils.toString(list, " ");
							grams = EngLemmatizer.lemmatize(grams);
							
							MapUtils.add2Map(map, grams);
							System.err.println(grams);
						}
						list = new ArrayList<String>();
						continue;
					}

					list.add(arr[i]);
				}

				if (list.size() > 1)
					MapUtils.add2Map(map, StringUtils.toString(list, " "));
			}
		}

		FileUtils.toFile(MapUtils.toString1(map, System.lineSeparator()), "/Users/craigtrim/Desktop/bigrams.txt",
				Codepage.UTF_8);
	}
}
