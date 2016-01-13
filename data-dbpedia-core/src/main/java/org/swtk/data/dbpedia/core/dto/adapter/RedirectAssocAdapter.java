package org.swtk.data.dbpedia.core.dto.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.swtk.data.dbpedia.core.dto.RedirectAssoc;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class RedirectAssocAdapter {

	public static String toString(Collection<RedirectAssoc> collection) {
		StringBuilder sb = new StringBuilder();

		Iterator<RedirectAssoc> iter = collection.iterator();
		while (iter.hasNext()) {
			sb.append(toString(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public static String toString(RedirectAssoc redirectAssoc) {
		StringBuilder sb = new StringBuilder();

		sb.append(redirectAssoc.getCannonical());
		sb.append("\t");
		sb.append(StringUtils.toString(redirectAssoc.getVariations(), "\t"));

		return sb.toString();
	}

	public static Collection<RedirectAssoc> transform(Map<String, Collection<String>> map) throws AdapterValidationException {
		List<RedirectAssoc> list = new ArrayList<RedirectAssoc>();
		for (Map.Entry<String, Collection<String>> entry : map.entrySet())
			list.add(RedirectAssocAdapter.transform(entry.getKey(), entry.getValue()));

		return list;
	}

	public static RedirectAssoc transform(String cannonical, Collection<String> variations) throws AdapterValidationException {
		RedirectAssoc redirectAssoc = new RedirectAssoc();

		redirectAssoc.setCannonical(cannonical);
		redirectAssoc.setVariations(variations);

		return redirectAssoc;
	}
}
