package org.swtk.data.imdb.scrape;

import java.io.File;

import org.swtk.data.imdb.json.dto.RunnerContract;
import org.swtk.data.imdb.json.dto.adapter.RunnerContractAdapter;
import org.swtk.data.imdb.scrape.svc.SavePage;
import org.swtk.data.imdb.scrape.svc.ScrapeMovie;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;

public class Runner {

	public static LogManager logger = new LogManager(Runner.class);

	public static void main(String... args) throws Throwable {
		RunnerContract contract = RunnerContractAdapter.transform(args);
		for (int i = contract.getMin(); i < contract.getMax(); i++) {

			String temp = String.valueOf(i);
			while (temp.length() < 7)
				temp = "0" + temp;

			final String ID = "tt" + temp;

			String path = new SavePage().getPath(ID, contract.getBaseDirectory(), "all", "json");
			if (new File(path).exists()) {
				logger.debug("ID already exists (id = %s)", ID);
				continue;
			}

			String jsonResults = new ScrapeMovie().process(ID, contract);
			FileUtils.toFile(jsonResults, path, Codepage.UTF_8);
		}
	}
}
