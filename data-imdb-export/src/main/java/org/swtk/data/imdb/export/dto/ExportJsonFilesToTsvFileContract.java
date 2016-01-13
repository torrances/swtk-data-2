package org.swtk.data.imdb.export.dto;

public class ExportJsonFilesToTsvFileContract {

	private String inputDirectory;
	
	private String outputFile;

	public String getInputDirectory() {
		return inputDirectory;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setInputDirectory(String inputDirectory) {
		this.inputDirectory = inputDirectory;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
}
