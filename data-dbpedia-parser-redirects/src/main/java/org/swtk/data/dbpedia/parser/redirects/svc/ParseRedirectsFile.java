package org.swtk.data.dbpedia.parser.redirects.svc;

import java.io.File;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.swtk.data.dbpedia.core.dto.Redirect;
import org.swtk.data.dbpedia.core.dto.RedirectAssoc;
import org.swtk.data.dbpedia.parser.redirects.dmo.LineExtractor;
import org.swtk.data.dbpedia.parser.redirects.dmo.OutputFileWriter;
import org.swtk.data.dbpedia.parser.redirects.dmo.RedirectFiltering;
import org.swtk.data.dbpedia.parser.redirects.dmo.RedirectGrouping;

import com.trimc.blogger.commons.exception.BusinessException;

@Lazy
@Service
public class ParseRedirectsFile {

	@Autowired
	private LineExtractor lineExtractor;

	@Autowired
	private RedirectFiltering redirectFiltering;

	@Autowired
	private RedirectGrouping redirectGrouping;

	@Autowired
	private OutputFileWriter outputFileWriter;

	public void process(File in, File out) throws BusinessException {
		Collection<Redirect> redirects = null;

		redirects = lineExtractor.process(in);
		redirects = redirectFiltering.process(redirects);

		Collection<RedirectAssoc> redirectAssocs = redirectGrouping.process(redirects);
		outputFileWriter.process(out, redirectAssocs);
	}
}
