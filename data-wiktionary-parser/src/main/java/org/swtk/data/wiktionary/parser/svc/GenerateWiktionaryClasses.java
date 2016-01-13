package org.swtk.data.wiktionary.parser.svc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.swtk.common.dict.dto.wiktionary.Entry;
import org.swtk.common.dict.dto.wiktionary.adapter.EntryAdapter;
import org.swtk.common.dict.dto.wiktionary.adapter.iter.DefinitionsAdapter;
import org.swtk.common.framework.type.Alpha;
import org.swtk.data.wiktionary.parser.dmo.EntryParser;
import org.swtk.data.wiktionary.parser.dmo.JavaGenerator;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.map.MapUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class GenerateWiktionaryClasses {

	public static LogManager logger = new LogManager(GenerateWiktionaryClasses.class);

	public static final String BASE_IN = "/Users/craigtrim/Desktop/wiktionary/03/";

	public Map<String, String> synonymQualifiersNotFound = new TreeMap<String, String>();

	public Map<String, String> languageCodesNotFound = new TreeMap<String, String>();

	public Map<String, String> fileNamesMap = new TreeMap<String, String>();

	private void add2Map(Map<String, List<Entry>> map, Entry entry, Alpha... alphas) {
		if (null == entry) return;
		final String KEY = asKey(alphas);

		List<Entry> innerList = (map.containsKey(KEY)) ? map.get(KEY) : new ArrayList<Entry>();
		innerList.add(entry);

		map.put(KEY, innerList);
	}

	private Entry asEntry(File file) throws BusinessException {
		String contents = FileUtils.toString(file, Codepage.UTF_8);

		if (!contents.contains("==English==")) return null;

		EntryParser parser = new EntryParser();
		Entry entry = parser.process(contents);
		languageCodesNotFound.putAll(parser.languageCodesNotFound);
		synonymQualifiersNotFound.putAll(parser.synonymQualifiersNotFound);

		if (DefinitionsAdapter.isEmpty(entry.getDefinitions())) return null;
		return entry;
	}

	private String asKey(Alpha... alphas) {
		StringBuilder sb = new StringBuilder();
		for (Alpha alpha : alphas)
			sb.append(alpha.lower());
		return sb.toString();
	}

	private List<File> getFiles(Alpha... alphas) {
		List<File> list = new ArrayList<File>();

		StringBuilder sb = new StringBuilder();
		sb.append(BASE_IN);
		for (Alpha alpha : alphas)
			sb.append(alpha.lower() + "/");

		File dir = new File(sb.toString());
		if (!dir.exists()) return list;

		for (File file : dir.listFiles()) {
			if (file.isDirectory()) continue;
			if (!"txt".equals(FileUtils.getExtension(file))) continue;
			list.add(file);
		}

		return list;
	}

	public void process() throws BusinessException {

		Map<String, List<Entry>> bufferMap = new TreeMap<String, List<Entry>>();

		/* 	start A1 iteration */
		for (Alpha a1 : Alpha.values()) {
			// if (Alpha.A != a1) continue;

			/* 	start A2 iteration */
			for (Alpha a2 : Alpha.values()) {
				// if (Alpha.P != a2) continue;

				for (File f2 : getFiles(a1, a2)) {
					Entry entry = asEntry(f2);
					if (!EntryAdapter.isEmpty(entry)) add2Map(bufferMap, entry, a1, a2);
				}

				/* 	start A3 iteration */
				for (Alpha a3 : Alpha.values()) {
					// if (Alpha.N != a3) continue;

					for (File f3 : getFiles(a1, a2, a3)) {
						Entry entry = asEntry(f3);
						if (!EntryAdapter.isEmpty(entry)) add2Map(bufferMap, entry, a1, a2, a3);
					}

					/* 	start A4 iteration */
					for (Alpha a4 : Alpha.values()) {
						// if (Alpha.O != a4) continue;

						for (File f4 : getFiles(a1, a2, a3, a4)) {
							Entry entry = asEntry(f4);
							if (!EntryAdapter.isEmpty(entry)) add2Map(bufferMap, entry, a1, a2, a3);
						}

						/* 	start A5 iteration */
						for (Alpha a5 : Alpha.values()) {
							for (File f5 : getFiles(a1, a2, a3, a4, a5)) {
								Entry entry = asEntry(f5);
								if (!EntryAdapter.isEmpty(entry)) add2Map(bufferMap, entry, a1, a2, a3);
							}

						} /* end A5 iteration */
					} /* end A4 iteration */

					/*	flush out map */
					for (Map.Entry<String, List<Entry>> entry : bufferMap.entrySet())
						writeToFile(entry.getValue(), a1, a2, a3);
					bufferMap = new HashMap<String, List<Entry>>();

				} /* end A3 iteration */

				/*	flush out map */
				for (Map.Entry<String, List<Entry>> entry : bufferMap.entrySet())
					// fileNamesMap.put(asKey(a1, a2), new JavaGenerator().writeToFile(entry.getValue(), a1, a2));
					writeToFile(entry.getValue(), a1, a2);
				bufferMap = new HashMap<String, List<Entry>>();

			} /* end A2 iteration */

			/*	flush out map */
			for (Map.Entry<String, List<Entry>> entry : bufferMap.entrySet())
				// fileNamesMap.put(asKey(a1), new JavaGenerator().writeToFile(entry.getValue(), a1));
				writeToFile(entry.getValue(), a1);
			bufferMap = new HashMap<String, List<Entry>>();

		} /* end A1 iteration */

		FileUtils.toFile(MapUtils.toString1(languageCodesNotFound, System.lineSeparator()), "/Users/craigtrim/Desktop/language-codes-not-found.dat", Codepage.UTF_8);
		FileUtils.toFile(MapUtils.toString1(synonymQualifiersNotFound, System.lineSeparator()), "/Users/craigtrim/Desktop/synonym-qualifiers-not-found.dat", Codepage.UTF_8);
	}

	private void writeToFile(List<Entry> entries, Alpha... alphas) throws BusinessException {
		String fileName = new JavaGenerator().writeToFile(entries, alphas);
		if (StringUtils.hasValue(fileName)) {
			fileNamesMap.put(asKey(alphas), fileName);
		}
	}
}
