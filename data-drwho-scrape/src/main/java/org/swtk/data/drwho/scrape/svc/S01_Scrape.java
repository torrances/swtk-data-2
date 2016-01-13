package org.swtk.data.drwho.scrape.svc;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.trimc.blogger.commons.utils.string.StringUtils;

public class S01_Scrape {

	public static void main(String... args) throws Throwable {

		for (int season = 0; season < 50; season++) {
			for (int episode = 0; episode < 50; episode++) {

				try {
					String input = String.format("%s-%s.htm", season, episode);
					String output = StringUtils.pad(season, 2) + "_" + StringUtils.pad(episode, 2) + ".htm";
					FileUtils.copyURLToFile(new URL("http://www.chakoteya.net/doctorwho/" + input), new File("/Users/craigtrim/Desktop/drwho/01/" + output));
				} catch (FileNotFoundException e) {}
			}
		}
	}
}
