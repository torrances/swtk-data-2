package org.swtk.data.fb.scrape.dmo;

import java.io.File;

import org.swtk.data.core.dmo.UserAgentProvider;

import com.gistlabs.mechanize.document.html.HtmlDocument;
import com.gistlabs.mechanize.impl.MechanizeAgent;
import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public class PageRetriever {

	public static LogManager logger = new LogManager(PageRetriever.class);

	public static void get(String url, File file) throws BusinessException {
		try {

			if (!file.exists()) {

				MechanizeAgent agent = new MechanizeAgent();
				agent.setUserAgent(UserAgentProvider.getInstance().random());
				HtmlDocument page = agent.get(url);

				String text = page.saveToString();
				if (text.contains("<title id=\"pageTitle\">Security Check Required")) {
					throw new BusinessException("Rate Limited (url = %s, target-path = %s)", url, file.getAbsolutePath());
				}

				page.saveTo(file);
				logger.debug("Retrieved URL (url = %s, target-path = %s)", url, file.getAbsolutePath());

				//				Thread.sleep(MathUtils.random(5500, 13500));
			}

		} catch (Exception e) {
			logger.error(e);
			throw new BusinessException("Couldn't retrieved URL (url = %s, target-path = %s)", url, file.getAbsolutePath());
		}
	}
}
