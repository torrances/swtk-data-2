package org.swtk.data.imdb.json.dto.adapter;

import java.io.File;

import org.swtk.data.imdb.json.dto.RunnerContract;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class RunnerContractAdapter {

	public static LogManager logger = new LogManager(RunnerContractAdapter.class);

	private final static int MAX = 9999999;

	private static Integer toInt(String arg) throws AdapterValidationException {
		try {
			return Integer.parseInt(arg);
		} catch (NumberFormatException e) {
			logger.error(e);
			throw new AdapterValidationException("First Parameter must be a valid int");
		}
	}

	public static RunnerContract transform(String... args) throws AdapterValidationException {
		RunnerContract contract = new RunnerContract();
		if (null == args || args.length < 1)
			throw new AdapterValidationException("Please supply a starting param (1 - %s)", MAX);
		if (args.length != 2)
			throw new AdapterValidationException("Second parameter must be a directory to store HTML files");

		int min = toInt(args[0]);
		if (min < 0)
			throw new AdapterValidationException("Parameter must be greater than 0");
		if (min > MAX)
			throw new AdapterValidationException("Parameter must be less than %s", MAX);

		contract.setMin(min);
		contract.setMax(MAX);
		contract.setBaseDirectory(args[1]);
		
		File base = new File(args[1]);
		if (!base.exists()) {
			base.mkdirs();
			logger.debug("Created base directory (%s)", base.getAbsolutePath());
		}

		return contract;
	}
}
