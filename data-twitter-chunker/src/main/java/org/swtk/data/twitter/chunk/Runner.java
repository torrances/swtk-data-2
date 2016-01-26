package org.swtk.data.twitter.chunk;

import org.swtk.data.twitter.chunk.dto.ChunkTweetFileContract;
import org.swtk.data.twitter.chunk.dto.adapter.ChunkTweetFileContractAdapter;
import org.swtk.data.twitter.chunk.svc.ChunkTweetFile;

import com.trimc.blogger.commons.LogManager;

public final class Runner {

	public static LogManager logger = new LogManager(Runner.class);

	private static String echo(String... args) {
		StringBuilder sb = new StringBuilder();

		sb.append("Incoming Paramters:");
		sb.append(String.format("\n\t#1 (input-directory): 			%s", args[0]));
		sb.append(String.format("\n\t#2 (output-directory): 			%s", args[1]));
		sb.append(String.format("\n\t#3 (number-of-tweets-per-file): 	%s", args[2]));
		sb.append(String.format("\n\t#4 (target-format): 			%s", args[3]));

		return sb.toString();
	}

	private static String error(String... args) {
		StringBuilder sb = new StringBuilder();

		sb.append("Expected 4 Paramters:");
		sb.append("\n\t#1 input-directory");
		sb.append("\n\t#2 output-directory");
		sb.append("\n\t#3 number-of-tweets-per-file");
		sb.append("\n\t#4 target-format");

		return sb.toString();
	}

	public static void main(String... args) throws Throwable {

		if (null != args) logger.info("\n%s", echo(args));
		if (null == args || 4 != args.length) logger.info("\n%s", error(args));

		ChunkTweetFileContract chunkTweetFileContract = ChunkTweetFileContractAdapter.transform(args);
		new ChunkTweetFile().process(chunkTweetFileContract);
	}
}
