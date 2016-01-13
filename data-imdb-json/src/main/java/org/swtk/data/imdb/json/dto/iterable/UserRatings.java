package org.swtk.data.imdb.json.dto.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.UserRating;
import org.swtk.data.imdb.json.dto.adapter.iterable.UserRatingsAdapter;

import com.google.gson.annotations.Expose;

public class UserRatings implements Iterable<UserRating>, Iterator<UserRating> {

	@Expose
	private List<UserRating> list;
	
	@Ignore
	public void add(UserRating... UserRatings) {
		for (UserRating UserRating : UserRatings)
			getUserRatings().add(UserRating);
	}
	
	public List<UserRating> getUserRatings() {
		if (null == list) setUserRatings(new ArrayList<UserRating>());
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
	public Iterator<UserRating> iterator() {
		return getUserRatings().iterator();
	}

	@Override
	public UserRating next() {
		return iterator().next();
	}

	private void setUserRatings(List<UserRating> list) {
		this.list = list;
	}

	@Ignore
	public int size() {
		return getUserRatings().size();
	}

	@Override
	@Ignore
	public String toString() {
		return UserRatingsAdapter.toTsv(this);
	}
}
