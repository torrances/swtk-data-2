package org.swtk.data.imdb.scrape.svc;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.swtk.data.imdb.scrape.dto.SavePageResult;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public class SavePage {

	public static LogManager logger = new LogManager(SavePage.class);

	public String getPath(String ID, String base, String type, String ext) {
		StringBuilder sb = new StringBuilder();

		sb.append(base);
		sb.append("/");
		sb.append(ext);
		sb.append("/");
		sb.append(ID.substring(2, 3));
		sb.append("/");
		sb.append(ID.substring(3, 4));
		sb.append("/");
		sb.append(ID.substring(4, 5));
		sb.append("/");
		sb.append(ID.substring(5, 6));
		sb.append("/");
		sb.append(ID.substring(6, 7));
		sb.append("/");
		sb.append(ID);
		sb.append("/");
		sb.append(type);
		sb.append(".");
		sb.append(ext);

		return sb.toString();
	}

	public SavePageResult process(final String URL, final String ID, String base, String type)
			throws BusinessException {

		SavePageResult savePageResult = new SavePageResult();

		savePageResult.setInFile(String.format("%s", URL, type));

		savePageResult.setOutHtml(new File(getPath(ID, base, type, "html")));
		savePageResult.setOutDat(new File(getPath(ID, base, type, "dat")));
		savePageResult.setOutJson(new File(getPath(ID, base, type, "json")));

		if (savePageResult.getOutDat().exists()) {
			logger.debug("File already exists (type = %s, id = %s, type = %s)", "dat", ID, type);
			savePageResult.setProcessed(true);
			return savePageResult;
		}

		if (!savePageResult.getOutHtml().exists()) {
			logger.debug("Downloading HTML (url = %s, type = %s)", savePageResult.getOutHtml(), type);
			try {
				FileUtils.copyURLToFile(new URL(savePageResult.getInFile()), savePageResult.getOutHtml());

			} catch (IOException e) {
				logger.error(e);
				throw new BusinessException("Unable to download HTML page (url = %s, type = %s)",
						savePageResult.getOutHtml(), type);
			}
		}

		try {
			savePageResult.setDoc(Jsoup.parse(savePageResult.getOutHtml(), "utf-8"));
			assertNotNull(savePageResult.getDoc());

		} catch (IOException e) {
			logger.error(e);
			throw new BusinessException("Unable to create Jsoup Document (url = %s, type = %s)",
					savePageResult.getOutHtml(), type);
		}

		savePageResult.setProcessed(false);
		return savePageResult;
	}
}
