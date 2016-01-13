package org.swtk.data.imdb.json.dto;

import org.junit.Ignore;
import org.swtk.data.imdb.json.dto.adapter.FaqEntryAdapter;

import com.google.gson.annotations.Expose;

public class FaqEntry {

	@Expose
	private String answer;
	
	@Expose
	private String id;
	
	@Expose
	private String question;

	public String getAnswer() {
		return answer;
	}

	public String getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	@Override
	@Ignore
	public int hashCode() {
		return this.toString().hashCode();
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	@Ignore
	public String toString() {
		return FaqEntryAdapter.toTsv(this);
	}
}
