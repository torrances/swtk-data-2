package org.swtk.data.dbpedia.parser.redirects.dmo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.swtk.data.dbpedia.core.dto.RedirectAssoc;
import org.swtk.data.dbpedia.core.dto.adapter.RedirectAssocAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

@Lazy
@Component
public class OutputFileWriter {

	public static LogManager logger = new LogManager(OutputFileWriter.class);

	public void process(File out, Collection<RedirectAssoc> redirectAssocs) throws BusinessException {

		try {

			java.io.FileWriter fw = new java.io.FileWriter(out.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for (RedirectAssoc redirectAssoc : redirectAssocs) {
				bw.write(RedirectAssocAdapter.toString(redirectAssoc));
				bw.write(System.lineSeparator());
			}

			bw.close();
			logger.debug("Redirect Writing Completed (path = %s, total = %s)", out.getAbsolutePath(), StringUtils.format(redirectAssocs.size()));

		} catch (IOException e) {
			throw new BusinessException("Unable to write to file (path = %s)", out.getAbsolutePath());
		}
	}
}
