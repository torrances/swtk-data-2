package org.swtk.data.twitter.chunk;

import org.swtk.data.twitter.chunk.dto.adapter.ChunkTweetFileContractAdapter;
import org.swtk.data.twitter.chunk.svc.ChunkTweetFile;
import org.swtk.data.twitter.core.dto.type.TwitterFormat;

public final class LocalRunner {

	public static void main(String... args) throws Throwable {
		 new ChunkTweetFile().process(
			ChunkTweetFileContractAdapter.transform(
				"/Users/craigtrim/data/Data/twitter/temp", 
				"/Users/craigtrim/data/Data/twitter/gnip/", 
				"100000",
				TwitterFormat.GNIP));
		 
		 // if this works, you can run a process of continuous reduction
		 // but if you do, you should also target an output format similar to the incoming one
		 // that means your param is "*" which signifies use the incoming data
		 // assuming incoming data is same format
	}
}
