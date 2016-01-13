package org.swtk.data.dbpedia.core.dto;

public class Redirect {

	private String subject;
	
	private String object;

	public String getObject() {
		return object;
	}

	public String getSubject() {
		return subject;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
