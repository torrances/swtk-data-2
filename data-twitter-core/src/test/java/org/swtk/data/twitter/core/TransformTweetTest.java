package org.swtk.data.twitter.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.swtk.data.twitter.core.dmo.TransformTweet;
import org.swtk.data.twitter.core.dto.gnip.GnipTweet;

import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;

public final class TransformTweetTest {

	@Test
	public void toCannonicalForm1() throws Throwable {
		
		File file = new File("../data-twitter-core/src/test/resources/tweets/gnip-1.json");
		assertTrue(file.exists());
		
		String json = FileUtils.toString(file, Codepage.UTF_8);
		assertNotNull(json);
		
		GnipTweet gnipTweet = new TransformTweet().toGnipForm(json);
		assertNotNull(gnipTweet);
	}
}
