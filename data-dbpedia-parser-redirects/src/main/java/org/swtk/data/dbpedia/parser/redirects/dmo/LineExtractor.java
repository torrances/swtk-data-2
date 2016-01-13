package org.swtk.data.dbpedia.parser.redirects.dmo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.swtk.data.dbpedia.core.dto.Redirect;
import org.swtk.data.dbpedia.core.dto.adapter.RedirectAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

@Lazy
@Component
public class LineExtractor {

	public static LogManager logger = new LogManager(LineExtractor.class);

	private Redirect transform(String line) throws BusinessException {
		String[] tokens = line.split(" ");
		return RedirectAdapter.transform(tokens[0], tokens[2]);
	}

	public Collection<Redirect> process(File file) throws BusinessException {
		List<Redirect> redirects = new ArrayList<Redirect>();

		try {

			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			String line = null;
			while ((line = br.readLine()) != null) {

				if (!StringUtils.hasValue(line)) continue;
				if (!line.contains("http://dbpedia.org/resource")) continue;

				Redirect redirect = transform(line);
				if (null != redirect) redirects.add(redirect);
			}

			br.close();

		} catch (IOException e) {
			throw new BusinessException("Failed to read file (path = %s)", file.getAbsolutePath());
		}

		logger.debug("Redirect Extraction Completed (total = %s)", StringUtils.format(redirects.size()));
		return redirects;
	}
}
