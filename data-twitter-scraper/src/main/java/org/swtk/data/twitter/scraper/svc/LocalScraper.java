package org.swtk.data.twitter.scraper.svc;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.twitter.core.dto.local.LocalIdentity;
import org.swtk.data.twitter.core.dto.local.LocalUser;
import org.swtk.data.twitter.core.dto.local.adapter.LocalIdentityAdapter;
import org.swtk.data.twitter.core.dto.local.adapter.LocalTweetAdapter;
import org.swtk.data.twitter.scraper.util.IdentityLookup;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.GsonUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class LocalScraper {

	public static String		BASE_DIR	= "/Users/craigtrim/Desktop/offline-twitter/";
	
	public static LogManager	logger		= new LogManager(LocalScraper.class);

	public static void main(String... args) {

		try {
			for (File file : FileUtils.getDescendantFilesInFolder(BASE_DIR + "/input", "html")) {
				if (!file.getAbsolutePath().endsWith("Twitter.html")) continue;

				Set<String> set = new TreeSet<String>();
				Document doc = parse(file, Codepage.UTF_8);

				for (Element p : doc.getElementsByTag("p"))
					for (Element tweet : p.getElementsByAttributeValueStarting("class", "TweetTextSize"))
						set.add(tweet.text());

				LocalUser user = new LocalUser();
				user.setIdentity(parseIdentity(doc));
				user.setTweets(LocalTweetAdapter.transform(set));

				writeToFile(user);
			}
			
			GatherMetrics.main();

		} catch (BusinessException e) {
			logger.error(e);
		}
	}

	public static Document parse(File file, Codepage codepage) throws BusinessException {
		try {

			return Jsoup.parse(file, codepage.getCharset().toString());

		} catch (IOException e) {
			logger.error(e);
			throw new BusinessException("Failed to parse file as jsoup.Doc (path = %s)", file.getAbsolutePath());
		}
	}

	public static LocalIdentity parseIdentity(Document doc) throws BusinessException {

		if (doc.getElementsByTag("title").isEmpty()) {
			throw new BusinessException("No TITLE found in doc");
		}

		String title = doc.getElementsByTag("title").first().text();
		String name = StringUtils.substringBefore(title, "(").trim();
		String handle = StringUtils.substringBefore(StringUtils.substringAfter(title, "("), ")").trim();

		return LocalIdentityAdapter.transform(name, handle, IdentityLookup.findAgeRange(handle), IdentityLookup.findGender(handle));
	}

	public static void writeToFile(LocalUser localUser) throws BusinessException {
		String path = BASE_DIR + "/output/" + localUser.getIdentity().getHandle() + ".json";
		String json = GsonUtils.toJsonSingleLineAll(localUser);

		FileUtils.toFile(json, path, Codepage.UTF_8);
	}
}
