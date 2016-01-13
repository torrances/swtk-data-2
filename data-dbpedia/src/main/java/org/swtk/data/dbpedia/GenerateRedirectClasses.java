package org.swtk.data.dbpedia;

import java.io.File;

import org.swtk.data.core.DataIocContainer;
import org.swtk.data.dbpedia.parser.redirects.svc.ParseRedirectsFile;
import org.swtk.data.dbpedia.parser.redirects.util.Constants;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;

public final class GenerateRedirectClasses {

	public static LogManager logger = new LogManager(GenerateRedirectClasses.class);

	public static void main(String... args) throws Throwable {

		File input = Constants.REDIRECTS;
		if (!input.exists()) throw new BusinessException("Input File does not exist (path = %s)", input.getAbsolutePath());

		if (!Constants.PARSED_OUTPUT.exists()) {
			ParseRedirectsFile parseRedirectsFile = (ParseRedirectsFile) DataIocContainer.getContext().getBean(ParseRedirectsFile.class);
			parseRedirectsFile.process(input, Constants.PARSED_OUTPUT);
		}
	}
}
