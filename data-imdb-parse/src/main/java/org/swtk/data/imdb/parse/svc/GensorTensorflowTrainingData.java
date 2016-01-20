package org.swtk.data.imdb.parse.svc;

import java.util.Map;
import java.util.TreeMap;

import org.swtk.data.imdb.json.dto.ImdbTitle;
import org.swtk.data.imdb.json.dto.Movie;
import org.swtk.data.imdb.parse.dmo.ImdbDeserializer;
import org.swtk.eng.preprocess.functions.CreateSentences;

import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.SetUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.map.MapUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

/**
 * @author craigtrim
 * 
 * Purpose:
 * 14-Jan-2015, generates an input file for tensorflow nn
 * uses IMDB json files as input
 *
 */
public class GensorTensorflowTrainingData {

	public static String TRAINING = "/Users/craigtrim/workspaces/public/skflow/imdb/output/training.csv";

	public static String TESTING = "/Users/craigtrim/workspaces/public/skflow/imdb/output/testing.csv";

	public static String WORKING = "/Users/craigtrim/workspaces/public/skflow/imdb/output/working.csv";

	public static String OUT_METRICS = "/Users/craigtrim/Desktop/metrics.txt";

	static Map<String, Integer> GENRES = new TreeMap<String, Integer>();

	static {
		GENRES.put("drama", 1);
		GENRES.put("comedy", 2);
		GENRES.put("romance", 3);
		GENRES.put("crime", 4);
		GENRES.put("short", 5);
		GENRES.put("animation", 6);
		GENRES.put("family", 7);
		GENRES.put("mystery", 8);
		GENRES.put("musical", 9);
		GENRES.put("adventure", 10);
	}

	public static void main(String... args) throws Throwable {

		StringBuilder sbTraining = new StringBuilder();
		StringBuilder sbTesting = new StringBuilder();
		StringBuilder sbWorking = new StringBuilder();

		Map<String, String> metrics = new TreeMap<String, String>();

		int ctr = 0;

		String IN = "/Users/craigtrim/Data/data/imdb/json/";
		for (ImdbTitle imdbTitle : ImdbDeserializer.process(IN)) {
			ctr++;

			if (!imdbTitle.hasMovies()) continue;
			for (Movie movie : imdbTitle.getMovies().getMovies()) {

				if (!movie.hasGenres()) continue;
				if (!movie.hasDescription()) continue;

				String description = normalize(movie.getDescription());
				if (StringUtils.isEmpty(description)) continue;

				for (String genre : movie.getGenres()) {

					genre = genre.toLowerCase();
					if (genre.startsWith("certificate:")) continue;

					if (SetUtils.memberOf(genre, GENRES.keySet())) {
						if (0 == ctr % 100) sbWorking.append(toString(genre, description));
						else if (0 == ctr % 10) sbTesting.append(toString(genre, description));
						else sbTraining.append(toString(genre, description));
					}

					MapUtils.add2Map(metrics, genre);
				}

				if (sbTraining.length() > 5000) {
					FileUtils.toFile(sbTraining, TRAINING, true, Codepage.UTF_8);
					sbTraining = new StringBuilder();
				}
			}
		}

		FileUtils.toFile(sbTraining, TRAINING, true, Codepage.UTF_8);
		FileUtils.toFile(sbTesting, TESTING, true, Codepage.UTF_8);
		FileUtils.toFile(sbWorking, WORKING, true, Codepage.UTF_8);

		FileUtils.toFile(MapUtils.toString1(metrics, "\n"), OUT_METRICS, true, Codepage.UTF_8);
	}

	private static String normalize(String description) throws BusinessException {
		StringBuilder sb = new StringBuilder();

		for (String sentence : CreateSentences.process(description)) {
			String modified = sentence.toLowerCase();
			if (modified.startsWith("with ")) continue;
			if (modified.startsWith("directed by ")) continue;
			if (modified.startsWith("created by ")) continue;
			sentence = sentence.replaceAll("\"", "");
			sentence = sentence.replaceAll(", ", " and ");

			sb.append(sentence + " ");
		}

		return sb.toString();
	}

	private static String toString(String genre, String description) {
		StringBuilder sb = new StringBuilder();

		sb.append(GENRES.get(genre));
		sb.append(",\"");
		sb.append(genre);
		sb.append("\",\"");
		sb.append(description);
		sb.append("\"");
		sb.append(System.lineSeparator());

		return sb.toString();
	}
}
