package org.swtk.data.imdb.json.dto;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.adapter.KeywordAdapter;

import com.google.gson.annotations.Expose;

public class Keyword {

	@Expose
	private int positiveVotes;
	
	@Expose
	private String text;
	
	@Expose
	private int totalVotes;

	public int getPositiveVotes() {
		return positiveVotes;
	}

	public String getText() {
		return text;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	public void setPositiveVotes(int positiveVotes) {
		this.positiveVotes = positiveVotes;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	@Override
	@Ignore
	public String toString() {
		return KeywordAdapter.toTsv(this);
	}
}
