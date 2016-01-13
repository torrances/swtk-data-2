package org.swtk.data.imdb.json.dto.adapter;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.CastMember;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class CastMemberAdapter {

	public static String toTsv(CastMember cast) {
		StringBuilder sb = new StringBuilder();

		sb.append(cast.getName());
		sb.append("\t");
		sb.append(cast.getCharacter());
		sb.append("\t");
		sb.append(cast.getRole());
		sb.append("\t");
		sb.append(cast.isCredited());

		return sb.toString();
	}
	
	public static String toTsv(Collection<CastMember> list) {
		StringBuilder sb = new StringBuilder();
		
		Iterator<CastMember> iter = list.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}

	public static CastMember transform(String name, String character, String role, boolean isCredited, boolean isStar) throws AdapterValidationException {
		CastMember cast = new CastMember();

		cast.setName(name);
		cast.setCharacter(character);
		cast.setRole(role);
		cast.setCredited(isCredited);
		/*cast.setStar(isStar);*/

		return cast;
	}
}
