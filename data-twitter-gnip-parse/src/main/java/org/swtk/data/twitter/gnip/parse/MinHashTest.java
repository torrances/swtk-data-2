package org.swtk.data.twitter.gnip.parse;

import java.util.Set;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.utils.SetUtils;

public class MinHashTest {

	public static LogManager logger = new LogManager(MinHashTest.class);

	public static void main(String... args) throws Throwable {

		Set<String> tweets = new DeserializeTweets().process(0, 100);
		String[] arr = (String[]) tweets.toArray(new String[tweets.size()]);

		// logger.info("Loaded Tweets (total = %s)\n%s", arr.length, StringUtils.toString(tweets, "\n"));

		double[][] results = new double[arr.length][arr.length];

		final int MAX = 20;
		for (int i = 0; i < MAX; i++) {
			Set<String> s1 = SetUtils.toSet(arr[i]);

			for (int j = i; j < MAX; j++) {
				Set<String> s2 = SetUtils.toSet(arr[j]);

				MinHash<String> min = new MinHash<String>(10);
				results[i][j] = min.similarity(s1, s2);
			}
		}

		for (int i = 0; i < results.length; i++) {
			for (int j = 0; j < results.length; j++) {
				if (results[i][j] > 0) {
					
					
					logger.info("i = %s, j = %s, results = %s\n\t%s\n\t%s", i, j, results[i][j], arr[i], arr[j]);
				}
			}
		}

		//		
		//		
		//		
		//		
		//		
		//		System.err.println(r);
	}
}
