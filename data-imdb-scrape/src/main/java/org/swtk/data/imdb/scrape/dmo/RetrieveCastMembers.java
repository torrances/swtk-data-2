package org.swtk.data.imdb.scrape.dmo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.swtk.data.imdb.json.dto.adapter.CastMemberAdapter;
import org.swtk.data.imdb.json.dto.adapter.iterable.CastMembersAdapter;
import org.swtk.data.imdb.json.dto.iterable.CastMembers;
import org.swtk.data.imdb.scrape.dto.SavePageResult;
import org.swtk.data.imdb.scrape.svc.SavePage;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class RetrieveCastMembers {

	public static LogManager logger = new LogManager(RetrieveCastMembers.class);

	public static final String URL_TEMPLATE = "http://www.imdb.com/title/%s/fullcredits?ref_=tt_cl_sm#cast";

	private static String getCharacter(Element element) {
		for (Element sibling : element.siblingElements())
			if ("character".equals(sibling.attr("class")))
				return StringUtils.trim(sibling.text());
		return null;
	}

	private CastMembers parse(Document doc) throws BusinessException {
		CastMembers castMembers = new CastMembers();

		for (Element element : doc.getElementsByAttributeValue("itemtype", "http://schema.org/Person")) {

			String character = getCharacter(element);
			boolean isCredited = character.contains("uncredited") ? false : true;
			character = (StringUtils.contains(character, "(")) ? StringUtils.substringBefore(character, "(").trim()
					: character;

			boolean isStar = false;

			for (String aCharacter : character.split("/")) {
				castMembers.add(CastMemberAdapter.transform(element.text(), aCharacter, element.attr("itemprop"),
						isCredited, isStar));
			}
		}

		return castMembers;
	}

	public CastMembers process(String id, String base) throws BusinessException {

		String URL = String.format(URL_TEMPLATE, id);
		SavePageResult savePageResult = new SavePage().process(URL, id, base, "cast");

		CastMembers castMembers = parse(savePageResult.getDoc());
		if (castMembers.isEmpty()) {
			logger.error("No Results Found (id = %s", id);
			return castMembers;
		}

		String results = CastMembersAdapter.toTsv(castMembers);
		FileUtils.toFile(results, savePageResult.getOutDat().getAbsolutePath(), Codepage.UTF_8);

		return castMembers;
	}
}
