package org.swtk.data.imdb.json.dto.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.CastMember;
import org.swtk.data.imdb.json.dto.adapter.iterable.CastMembersAdapter;

import com.google.gson.annotations.Expose;

public class CastMembers implements Iterable<CastMember>, Iterator<CastMember> {

	@Expose
	private List<CastMember> list;
	
	@Ignore
	public void add(CastMember... CastMembers) {
		for (CastMember CastMember : CastMembers)
			getCastMembers().add(CastMember);
	}
	
	public List<CastMember> getCastMembers() {
		if (null == list) setCastMembers(new ArrayList<CastMember>());
		return list;
	}

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public boolean hasNext() {
		return iterator().hasNext();
	}

	@Ignore
	public boolean isEmpty() {
		return !iterator().hasNext();
	}

	@Override
	@Ignore
	public Iterator<CastMember> iterator() {
		return getCastMembers().iterator();
	}

	@Override
	public CastMember next() {
		return iterator().next();
	}

	private void setCastMembers(List<CastMember> list) {
		this.list = list;
	}

	@Ignore
	public int size() {
		return getCastMembers().size();
	}

	@Override
	@Ignore
	public String toString() {
		return CastMembersAdapter.toTsv(this);
	}
}
