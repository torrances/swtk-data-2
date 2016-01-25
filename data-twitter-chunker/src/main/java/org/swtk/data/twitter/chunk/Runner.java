package org.swtk.data.twitter.chunk;

import org.swtk.data.twitter.chunk.dto.ChunkTweetFileContract;
import org.swtk.data.twitter.chunk.dto.adapter.ChunkTweetFileContractAdapter;
import org.swtk.data.twitter.chunk.svc.ChunkTweetFile;

public final class Runner {

	public static void main(String... args) throws Throwable {
		ChunkTweetFileContract chunkTweetFileContract = ChunkTweetFileContractAdapter.transform(args);
		new ChunkTweetFile().process(chunkTweetFileContract);
	}
}
