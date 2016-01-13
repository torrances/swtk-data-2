package org.swtk.data.imdb.export.dto.adapter;

import java.io.File;

import org.swtk.data.imdb.export.dto.ExportJsonFilesToTsvFileContract;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.utils.string.StringUtils;

public final class ExportJsonFilesToTsvFileContractAdapter {

	public static LogManager logger = new LogManager(ExportJsonFilesToTsvFileContractAdapter.class);

	public static ExportJsonFilesToTsvFileContract tranform(String inputDirectory, String outputFile)
			throws AdapterValidationException {
		ExportJsonFilesToTsvFileContract contract = new ExportJsonFilesToTsvFileContract();

		contract.setInputDirectory(inputDirectory);
		contract.setOutputFile(outputFile);

		if (!new File(contract.getInputDirectory()).exists())
			throw new AdapterValidationException("Input Directory does not exist (path = %s)", inputDirectory);

		while (new File(contract.getOutputFile()).exists()) {
			String original = contract.getOutputFile();
			String name = StringUtils.substringBefore(contract.getOutputFile(), ".tsv");
			name = name + "-" + System.currentTimeMillis() + ".tsv";
			contract.setOutputFile(name);
			logger.warn("Output file name changed (original = %s, modified = %s)", original, contract.getOutputFile());
		}

		return contract;
	}
}
