package org.swtk.data.twitter.chunk.dto.adapter;

import java.io.File;

import org.swtk.data.twitter.chunk.dto.ChunkTweetFileContract;
import org.swtk.data.twitter.core.dto.type.TwitterFormat;

import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.file.FileUtils;

public final class ChunkTweetFileContractAdapter {

	public static ChunkTweetFileContract transform(File inputFileDirectory, File outputFileDirectory, Integer numberOfTweetsPerFile, TwitterFormat targetFormat) throws AdapterValidationException {
		ChunkTweetFileContract contract = new ChunkTweetFileContract();

		if (!inputFileDirectory.exists()) {
			throw new AdapterValidationException("Input File Directory does not exist (path = %s)", inputFileDirectory.getAbsolutePath());
		}

		contract.setInputFiles(FileUtils.getFilesFromPath(inputFileDirectory));

		if (0 == contract.getInputFiles().size()) {
			throw new AdapterValidationException("No input files found (path = %s)", inputFileDirectory.getAbsolutePath());
		}

		contract.setOutputFilePathTemplate(outputFileDirectory.getAbsolutePath() + "/%s.dat");
		contract.setOutputFileDirectory(outputFileDirectory);
		contract.setTargetFormat(targetFormat);

		contract.setNumberOfTweetsPerFile(numberOfTweetsPerFile);
		if ("*".equals(contract.getNumberOfTweetsPerFile())) {
			contract.setNumberOfTweetsPerFile(Integer.MAX_VALUE);
		}

		if (contract.getNumberOfTweetsPerFile() < 1) {
			throw new AdapterValidationException("Number of Tweets per file must be greater than 0 (param-value = %s)", numberOfTweetsPerFile);
		}

		if (!contract.getOutputFileDirectory().exists()) {
			contract.getOutputFileDirectory().mkdirs();
		}

		return contract;
	}

	public static ChunkTweetFileContract transform(String... args) throws AdapterValidationException {
		if (4 != args.length) throw new AdapterValidationException("Invalid Parameter Set");

		return transform(args[0], args[1], args[2], args[3]);
	}

	public static ChunkTweetFileContract transform(String inputFile, String outputFile, Integer numberOfTweetsPerFile, TwitterFormat targetFormat) throws AdapterValidationException {
		return transform(new File(inputFile), new File(outputFile), numberOfTweetsPerFile, targetFormat);
	}

	public static ChunkTweetFileContract transform(String inputFile, String outputFile, String strNumberOfTweetsPerFile, String strTargetFormat) throws AdapterValidationException {
		try {

			int numberOfTweetsPerFile = ("*".equals(strNumberOfTweetsPerFile)) ? Integer.MAX_VALUE : Integer.parseInt(strNumberOfTweetsPerFile);
			
			TwitterFormat targetFormat = TwitterFormat.find(strTargetFormat);
			if (null == targetFormat) throw new AdapterValidationException("Invalid Target Format (permissable-values = %s)", TwitterFormat.list());
			
			return transform(inputFile, outputFile, numberOfTweetsPerFile, targetFormat);

		} catch (NumberFormatException e) {
			throw new AdapterValidationException("Parameter Data Type (number-of-tweets) must be an Integer");
		}
	}
}
