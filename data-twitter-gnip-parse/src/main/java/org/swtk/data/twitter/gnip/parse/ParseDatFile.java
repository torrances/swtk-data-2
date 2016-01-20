package org.swtk.data.twitter.gnip.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Set;
import java.util.TreeSet;

import org.swtk.data.twitter.core.dmo.TransformGnipTweet;
import org.swtk.data.twitter.core.dto.generic.Tweet;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class ParseDatFile {

	public static LogManager logger = new LogManager(ParseDatFile.class);

	public static void main(String... args) throws Throwable {

		Set<String> set = new TreeSet<String>();
		Path out = Paths.get("/Users/craigtrim/data/Data/twitter/out.dat");

		try (BufferedReader br = new BufferedReader(new FileReader(new File("/Users/craigtrim/data/Data/twitter/20160119_0644.dat")))) {
			String line;

			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				if (!StringUtils.hasValue(line)) continue;

				sb.append(line);
				if (!line.endsWith("}")) continue;

				try {
					Tweet tweet = new TransformGnipTweet().transform(StringUtils.trim(sb.toString()));
					if (tweet.hasText()) set.add(tweet.getNormalizedNoHashtagsOrURLs());

				} catch (Exception e) {
					logger.error(e, line);
				}

				if (set.size() > 10000) {
					Files.write(out, set, Codepage.UTF_8.getCharset(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
					set = new TreeSet<String>();
				}

				sb = new StringBuilder();
			}
		}

		Files.write(out, set, Codepage.UTF_8.getCharset(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
	}
}
