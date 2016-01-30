package org.swtk.data.twitter.core.dto.local;

import java.util.Collection;

import com.google.gson.annotations.Expose;

public class LocalUser {

	@Expose
	private LocalIdentity identity;
	
	@Expose
	private Collection<LocalTweet> tweets;

	public LocalIdentity getIdentity() {
		return identity;
	}

	public Collection<LocalTweet> getTweets() {
		return tweets;
	}

	public void setIdentity(LocalIdentity identity) {
		this.identity = identity;
	}

	public void setTweets(Collection<LocalTweet> tweets) {
		this.tweets = tweets;
	}
}
