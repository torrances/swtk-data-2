package org.swtk.data.twitter.chunk.dto;

import java.io.File;
import java.util.Collection;

import org.swtk.data.twitter.core.dto.type.TwitterFormat;

/**
 * @author craigtrim
 *
 * Purpose:
 * inputFileDirectory:		the directory of input twitter files 
 * 							in any format (as long as that format is consistent across all files)
 * 							recognized formats are contained in {@link TwitterFormat}
 * outputFileDirectory:		the directory for chunked output files in a specified format
 * numberOfTweetsPerFile:	the number of tweets in each output file
 * 							this may be smaller or larger than the number of tweets in each input file
 * targetFormat:			the format of the JSON serialized to each output file
 *
 */
public class ChunkTweetFileContract {

	private String outputFilePathTemplate;

	private Collection<File> inputFiles;

	private File outputFileDirectory;

	private Integer numberOfTweetsPerFile;

	private TwitterFormat targetFormat;

	public Collection<File> getInputFiles() {
		return inputFiles;
	}

	public Integer getNumberOfTweetsPerFile() {
		return numberOfTweetsPerFile;
	}

	public File getOutputFileDirectory() {
		return outputFileDirectory;
	}

	public String getOutputFilePathTemplate() {
		return outputFilePathTemplate;
	}

	public TwitterFormat getTargetFormat() {
		return targetFormat;
	}

	public void setInputFiles(Collection<File> inputFiles) {
		this.inputFiles = inputFiles;
	}

	public void setNumberOfTweetsPerFile(Integer numberOfTweetsPerFile) {
		this.numberOfTweetsPerFile = numberOfTweetsPerFile;
	}

	public void setOutputFileDirectory(File outputFileDirectory) {
		this.outputFileDirectory = outputFileDirectory;
	}

	public void setOutputFilePathTemplate(String outputFilePathTemplate) {
		this.outputFilePathTemplate = outputFilePathTemplate;
	}

	public void setTargetFormat(TwitterFormat targetFormat) {
		this.targetFormat = targetFormat;
	}
}
