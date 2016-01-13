package org.swtk.data.imdb.scrape.dto;

import java.io.File;

import org.jsoup.nodes.Document;

public class SavePageResult {

	private Document doc;

	private String inFile;

	private File outDat;

	private File outHtml;

	private File outJson;

	private boolean processed;

	public Document getDoc() {
		return doc;
	}

	public String getInFile() {
		return inFile;
	}

	public File getOutDat() {
		return outDat;
	}

	public File getOutHtml() {
		return outHtml;
	}

	public File getOutJson() {
		return outJson;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public void setInFile(String inFile) {
		this.inFile = inFile;
	}

	public void setOutDat(File outDat) {
		this.outDat = outDat;
	}

	public void setOutHtml(File outHtml) {
		this.outHtml = outHtml;
	}

	public void setOutJson(File outJson) {
		this.outJson = outJson;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
