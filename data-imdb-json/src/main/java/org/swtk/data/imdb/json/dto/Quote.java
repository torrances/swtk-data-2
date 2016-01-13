package org.swtk.data.imdb.json.dto;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.adapter.QuoteAdapter;

import com.google.gson.annotations.Expose;

public class Quote {

	@Expose
	private String character;

	@Expose
	private String text;

	public String getCharacter() {
		return character;
	}

	public String getText() {
		return text;
	}

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	@Ignore
	public String toString() {
		return QuoteAdapter.toTsv(this);
	}
}
