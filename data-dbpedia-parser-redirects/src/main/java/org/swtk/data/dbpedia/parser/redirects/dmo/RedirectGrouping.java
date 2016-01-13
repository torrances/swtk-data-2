package org.swtk.data.dbpedia.parser.redirects.dmo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.swtk.data.dbpedia.core.dto.Redirect;
import org.swtk.data.dbpedia.core.dto.RedirectAssoc;
import org.swtk.data.dbpedia.core.dto.adapter.RedirectAssocAdapter;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.string.StringUtils;

@Lazy
@Component
public class RedirectGrouping {

	public static LogManager logger = new LogManager(RedirectGrouping.class);

	public Collection<RedirectAssoc> process(Collection<Redirect> redirects) throws BusinessException {
		Map<String, Collection<String>> map = new TreeMap<String, Collection<String>>();

		for (Redirect redirect : redirects) {

			Collection<String> innerList = map.containsKey(redirect.getObject()) ? map.get(redirect.getObject()) : new ArrayList<String>();
			innerList.add(redirect.getSubject());
			map.put(redirect.getObject(), innerList);
		}

		logger.debug("Redirect Grouping Completed (total cannonical values = %s)", StringUtils.format(map.size()));
		return RedirectAssocAdapter.transform(map);
	}
}
