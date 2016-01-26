package org.swtk.data.twitter.chunk;

import org.swtk.data.twitter.chunk.dto.ChunkTweetFileContract;
import org.swtk.data.twitter.chunk.dto.adapter.ChunkTweetFileContractAdapter;
import org.swtk.data.twitter.chunk.svc.ChunkTweetFile;

import com.trimc.blogger.commons.LogManager;

public final class Runner {

	public static LogManager logger = new LogManager(Runner.class);

	private static String echo(String... args) {
		StringBuilder sb = new StringBuilder();

		sb.append("Incoming Paramters:\n");
		sb.append(String.format("\t#1 (input-directory): 			%s", args[0]));
		sb.append(String.format("\t#2 (output-directory): 			%s", args[1]));
		sb.append(String.format("\t#3 (number-of-tweets-per-file): 	%s", args[2]));
		sb.append(String.format("\t#4 (target-format): 				%s", args[3]));

		return sb.toString();
	}

	private static String error(String... args) {
		StringBuilder sb = new StringBuilder();

		sb.append("Expected 4 Paramters:\n");
		sb.append("\t#1 (input-directory)");
		sb.append("\t#2 (output-directory)");
		sb.append("\t#3 (number-of-tweets-per-file)");
		sb.append("\t#4 (target-format)");

		return sb.toString();
	}

	public static void main(String... args) throws Throwable {

		if (null != args) logger.info("%s", echo(args));
		if (null == args || 4 != args.length) logger.info("%s", error(args));

		ChunkTweetFileContract chunkTweetFileContract = ChunkTweetFileContractAdapter.transform(args);
		new ChunkTweetFile().process(chunkTweetFileContract);
	}
}
