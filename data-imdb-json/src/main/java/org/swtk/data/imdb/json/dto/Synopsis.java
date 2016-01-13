package org.swtk.data.imdb.json.dto;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.adapter.SynopsisAdapter;

import com.google.gson.annotations.Expose;

public class Synopsis {

	@Expose
	private String text;

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	@Ignore
	public String toString() {
		return SynopsisAdapter.toTsv(this);
	}
}
