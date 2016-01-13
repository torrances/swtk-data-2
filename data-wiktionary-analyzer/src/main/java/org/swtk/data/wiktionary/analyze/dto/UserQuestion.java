package org.swtk.data.wiktionary.analyze.dto;

public class UserQuestion {

	private String original;
	
	private String normalized;

	public String getNormalized() {
		return normalized;
	}

	public String getOriginal() {
		return original;
	}

	public void setNormalized(String normalized) {
		this.normalized = normalized;
	}

	public void setOriginal(String original) {
		this.original = original;
	}
}
