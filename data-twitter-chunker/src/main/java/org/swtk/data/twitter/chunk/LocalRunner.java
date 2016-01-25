package org.swtk.data.twitter.chunk;

import org.swtk.data.twitter.chunk.dto.adapter.ChunkTweetFileContractAdapter;
import org.swtk.data.twitter.chunk.svc.ChunkTweetFile;
import org.swtk.data.twitter.core.dto.type.TwitterFormat;

public final class LocalRunner {

	public static void main(String... args) throws Throwable {
		 new ChunkTweetFile().process(
			ChunkTweetFileContractAdapter.transform(
				"/Users/craigtrim/data/Data/twitter/20160119_0644.dat", 
				"/Users/craigtrim/data/Data/twitter/", 
				"1000",
				TwitterFormat.CANNONICAL));
	}
}
