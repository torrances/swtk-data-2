package org.swtk.data.dbpedia.parser.redirects.dmo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.swtk.data.dbpedia.core.dto.Redirect;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

@Lazy
@Component
public class RedirectFiltering {

	public static LogManager logger = new LogManager(RedirectFiltering.class);

	private boolean isInvalid(Redirect redirect) {

		if (redirect.getSubject().contains("/")) return true;

		return false;
	}

	private boolean isSimilar1(Redirect redirect, String punctuation) {

		String _subject = redirect.getSubject().replaceAll(punctuation, "");
		String _object = redirect.getObject().replaceAll(punctuation, "");

		if (_subject.equalsIgnoreCase(_object)) return true;
		if (_subject.equalsIgnoreCase(redirect.getObject())) return true;

		if (redirect.getSubject().equalsIgnoreCase(_object)) return true;
		if (redirect.getSubject().equalsIgnoreCase(redirect.getObject())) return true;

		return false;
	}

	private boolean isSimilar2(Redirect redirect, String predictate) {

		/*	filter out
		 * 	AfghanistanCommunications vs Communications_in_Afghanistan	*/
		if (redirect.getObject().contains(predictate)) {
			StringBuilder sb = new StringBuilder();
			sb.append(StringUtils.substringAfter(redirect.getObject(), predictate).trim());
			sb.append(StringUtils.substringBefore(redirect.getObject(), predictate).trim());
			if (sb.toString().equals(redirect.getSubject())) return true;
		}

		return false;
	}

	private boolean isSimilar3(Redirect redirect) {

		String subject = redirect.getSubject();
		subject = TextUtils.removePunctuation(subject);

		String object = redirect.getObject();
		object = TextUtils.removePunctuation(object);

		return subject.equalsIgnoreCase(object);
	}

	private boolean isSimilar4(Redirect redirect) {

		String subject = redirect.getSubject();
		subject = subject.replaceAll("\\(.*\\)", "");

		String object = redirect.getObject();
		object = object.replaceAll("\\(.*\\)", "");

		return subject.equalsIgnoreCase(object);
	}

	private boolean isSimilar5(Redirect redirect) {

		try {
			String subject = redirect.getSubject();
			subject = java.net.URLDecoder.decode(subject, "UTF-8");

			String object = redirect.getObject();
			subject = java.net.URLDecoder.decode(object, "UTF-8");

			return subject.equalsIgnoreCase(object);

		} catch (UnsupportedEncodingException e) {
			return false;
		}
	}

	public Collection<Redirect> process(Collection<Redirect> redirects) throws BusinessException {
		Collection<Redirect> filtered = new ArrayList<Redirect>();

		for (Redirect redirect : redirects) {

			if (isInvalid(redirect)) continue;
			if (isSimilar1(redirect, "_")) continue;
			if (isSimilar1(redirect, "-")) continue;
			if (isSimilar2(redirect, "_in_")) continue;
			if (isSimilar2(redirect, "_of_")) continue;
			if (isSimilar3(redirect)) continue;
			if (isSimilar4(redirect)) continue;
			if (isSimilar5(redirect)) continue;

			filtered.add(redirect);
		}

		logger.debug("Redirect Filtering Completed (original = %s, filtering = %s)", StringUtils.format(redirects.size()), StringUtils.format(filtered.size()));
		return filtered;
	}
}
