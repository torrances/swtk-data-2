package org.swtk.data.twitter.gnip.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.swtk.data.twitter.core.dmo.TransformTweet;
import org.swtk.data.twitter.core.dto.canonical.CannonicalTweet;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.SetUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class ParseDatFile {

	public static LogManager logger = new LogManager(ParseDatFile.class);

	//	public static int ctr = 1;

	public static Map<Integer, String> map = new TreeMap<Integer, String>();

	//	private static String key(int ctr) {
	//		return "1" + StringUtils.pad(ctr, 5);
	//	}

	public static void main(String... args) throws Throwable {}

	public Set<String> process(int min, int max) throws Throwable {

		// Path out = Paths.get("/Users/craigtrim/data/Data/twitter/out.dat");

		int lineCounter = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(new File("/Users/craigtrim/data/Data/twitter/20160119_0644.dat")))) {
			String line;

			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				if (!StringUtils.hasValue(line)) continue;

				if (lineCounter < min || lineCounter > max) continue;
				lineCounter++;

				sb.append(line);
				if (!line.endsWith("}")) continue;

				try {

					CannonicalTweet tweet = new TransformTweet().toCannonicalForm(StringUtils.trim(sb.toString()));

					String text = tweet.getNormalizedNoHashtagsOrURLs();
					if (StringUtils.hasValue(text) && text.length() > 10) {
						map.put(hash(text), text);
					}

				} catch (Exception e) {
					logger.error(e, line);
					throw new BusinessException("Unable to deserialize line:\n\t%s", sb.toString());
				}

				//				if (map.keySet().size() > 500000) {
				////					writeToFile(map, out);
				//					map = new TreeMap<Integer, String>();
				//				}

				//				sb = new StringBuilder();
			}
		}

		return SetUtils.toSet(map.values());
	}

	private static int hash(String text) {
		int hash = text.hashCode();
		return (hash > 0) ? hash : hash * -1;
	}

	//	private static void writeToFile(Map<Integer, String> map, Path out) throws IOException {
	//		Set<String> list = new TreeSet<String>();
	//		for (Integer key : map.keySet()) {
	//			list.add(key + " " + map.get(key));
	//		}
	//
	//		Files.write(out, list, Codepage.UTF_8.getCharset(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
	//	}
}
