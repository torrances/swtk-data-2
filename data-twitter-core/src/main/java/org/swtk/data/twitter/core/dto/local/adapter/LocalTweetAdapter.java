package org.swtk.data.twitter.core.dto.local.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.swtk.data.twitter.core.dto.local.LocalTweet;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class LocalTweetAdapter {

	public static Collection<LocalTweet> transform(Set<String> set) throws AdapterValidationException {
		Collection<LocalTweet> localTweets = new ArrayList<LocalTweet>();

		for (String text : set) {
			localTweets.add(LocalTweetAdapter.transform(text));
		}

		return localTweets;
	}

	public static LocalTweet transform(String text) throws AdapterValidationException {
		LocalTweet bean = new LocalTweet();

		bean.setText(text);

		return bean;
	}
}
