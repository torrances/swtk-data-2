package org.swtk.data.twitter.scraper.dmo;

import java.io.File;

import org.swtk.data.core.dmo.UserAgentProvider;

import com.gistlabs.mechanize.document.html.HtmlDocument;
import com.gistlabs.mechanize.impl.MechanizeAgent;
import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public class PageRetriever {

	public static LogManager logger = new LogManager(PageRetriever.class);

	public static void get(String url, File file) throws BusinessException {

		if (file.exists()) {
			logger.info("File already exists (path = %s)", file.getAbsolutePath());
			return;
		}

		try {

			MechanizeAgent agent = new MechanizeAgent();
			agent.setUserAgent(UserAgentProvider.getInstance().random());
			HtmlDocument page = agent.get(url);

			page.saveTo(file);
			logger.debug("Retrieved URL (url = %s, target-path = %s)", url, file.getAbsolutePath());

			// Thread.sleep(MathUtils.random(5500, 13500));

		} catch (

		Exception e)

		{
			logger.error(e);
			throw new BusinessException("Couldn't retrieved URL (url = %s, target-path = %s)", url, file.getAbsolutePath());
		}
	}
}
