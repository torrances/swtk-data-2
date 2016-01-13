package org.swtk.commons.dict.wordnet.indexbyid.instance.p#n1.p#n2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.swtk.common.dict.dto.wordnet.IndexNoun;

import com.trimc.blogger.commons.utils.GsonUtils;

public final class WordnetNounIndexIdInstance#n1#n2#n3#n4 {

	private static Map<String, Collection<IndexNoun>> map = new TreeMap<String, Collection<IndexNoun>>();

	static {
		#content
	}

	private static void add(final String ID, final String JSON) {
		IndexNoun indexNoun = GsonUtils.toObject(JSON, IndexNoun.class);
		Collection<IndexNoun> list = (map.containsKey(ID)) ? map.get(ID) : new ArrayList<IndexNoun>();
		list.add(indexNoun);
		map.put(ID, list);
	}
	
	public static Collection<IndexNoun> get(final String TERM) {
		return map.get(TERM);
	}

	public static boolean has(final String TERM) {
		return map.containsKey(TERM);
	}
	
	public static Collection<String> ids() {
		return map.keySet();
	}
}
