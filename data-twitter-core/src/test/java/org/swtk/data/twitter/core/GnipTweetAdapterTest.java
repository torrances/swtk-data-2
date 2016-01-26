package org.swtk.data.twitter.core;

import java.io.File;

import org.junit.Test;
import org.swtk.data.twitter.core.dto.gnip.GnipTweet;
import org.swtk.data.twitter.core.dto.gnip.adapter.GnipTweetAdapter;

import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import static org.junit.Assert.*;
public final class GnipTweetAdapterTest {

	@Test
	public void serialize1() throws Throwable {
		
		File file = new File("../data-twitter-core/src/test/resources/tweets/gnip-1.json");
		assertTrue(file.exists());
		
		String json = FileUtils.toString(file, Codepage.UTF_8);
		assertNotNull(json);
		
		GnipTweet tweet = GnipTweetAdapter.transform(json);
		assertNotNull(tweet);
		assertNotNull(tweet.getId());
	}
}
