package org.swtk.data.imdb.json.dto;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.adapter.UserRatingAdapter;

import com.google.gson.annotations.Expose;

public class UserRating {

	@Expose
	private String average;
	
	@Expose
	private String total;
	
	@Expose
	private String type;

	public String getAverage() {
		return average;
	}

	public String getTotal() {
		return total;
	}

	public String getType() {
		return type;
	}

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	public void setAverage(String average) {
		this.average = average;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	@Ignore
	public String toString() {
		return UserRatingAdapter.toTsv(this);
	}
}
