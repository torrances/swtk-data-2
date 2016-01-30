package org.swtk.data.gngrams.parse.dmo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.swtk.common.framework.type.Alpha;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;

public class GngramFileChunker {

	public static LogManager						logger		= new LogManager(GngramFileChunker.class);

	public Map<String, Map<String, List<String>>>	nextLines	= new HashMap<String, Map<String, List<String>>>();

	public final Integer							THRESHOLD	= 10000;

	private void addToFile(File outdir, Alpha alpha, String n1, String n2, String line) throws BusinessException {

		Map<String, List<String>> innerMap = nextLines.containsKey(n1) ? nextLines.get(n1) : new HashMap<String, List<String>>();
		List<String> innerList = innerMap.containsKey(n2) ? innerMap.get(n2) : new ArrayList<String>();

		innerList.add(line);

		if (innerList.size() >= THRESHOLD) {
			writeToFile(outdir, key(alpha, n1, n2), innerList);
			innerList = new ArrayList<String>();
		}

		innerMap.put(n2, innerList);
		nextLines.put(n1, innerMap);
	}

	private void iterate(Alpha alpha, File outdir, File file) throws BusinessException {
		try {

			try (FileInputStream fis = new FileInputStream(file)) {

				try (BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
					String line = reader.readLine();

					while (null != line) {
						if (line.length() < 3) continue;

						String n1 = line.toLowerCase().substring(1, 2).toLowerCase();
						String n2 = line.toLowerCase().substring(2, 3).toLowerCase();

						addToFile(outdir, alpha, n1, n2, line);
						line = reader.readLine();
					}
				}
			}

		} catch (Exception e) {
			logger.error(e);
			throw new BusinessException("File Iteration Failed (path = %s)", file.getAbsolutePath());
		}
	}

	private String key(Alpha alpha, String n1, String n2) {
		return alpha.lower() + n1 + n2;
	}

	public void process(Alpha alpha) throws BusinessException {

		File outdir = new File("/Users/craigtrim/data/Data/GG/eng/01/" + alpha.upper() + "/");
		if (!outdir.exists()) outdir.mkdirs();

		File file = new File("/Users/craigtrim/data/Data/GG/eng/01/googlebooks-eng-all-1gram-20120701-" + alpha.lower());
		iterate(alpha, outdir, file);

		// empty the cache
		for (Map.Entry<String, Map<String, List<String>>> e1 : nextLines.entrySet()) {
			for (Map.Entry<String, List<String>> e2 : e1.getValue().entrySet()) {

				String key = alpha.lower() + e1.getKey() + e2.getKey();
				writeToFile(outdir, key, e2.getValue());
			}
		}
	}

	private void writeToFile(File outdir, String key, Collection<String> collection) throws BusinessException {
		String path = outdir.getAbsolutePath() + "/" + key + ".dat";
		FileUtils.toFile(collection, path, true, Codepage.UTF_8);
	}
}
