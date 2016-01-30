package org.swtk.data.twitter.scraper.svc;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import org.swtk.data.twitter.core.dto.local.LocalUser;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;

public class GatherMetrics {

	public static String				INPUT	= "/Users/craigtrim/Desktop/offline-twitter/output";

	public static Map<String, Integer>	map		= new TreeMap<String, Integer>();

	public static void main(String... args) throws BusinessException {
		for (File file : FileUtils.getDescendantFilesInFolder(INPUT, "json")) {

			String json = FileUtils.toString(file, Codepage.UTF_8);
			assertNotNull(json);

			LocalUser localUser = GsonUtils.toObject(json, LocalUser.class);
			assertNotNull(localUser);

			StringBuilder sb = new StringBuilder();
			sb.append(localUser.getIdentity().getGender());
			sb.append("\t");
			sb.append(localUser.getIdentity().getAgeRange());

			Integer count = map.containsKey(sb.toString()) ? map.get(sb.toString()) : 0;
			count += localUser.getTweets().size();
			map.put(sb.toString(), count);
		}
		
		int total = 0;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.err.println(entry.getKey() + "\t" + entry.getValue());
			total += entry.getValue();
		}
		System.err.println(total);
	}
}
