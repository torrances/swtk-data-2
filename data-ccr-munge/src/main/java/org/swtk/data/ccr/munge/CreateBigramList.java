package org.swtk.data.ccr.munge;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.file.FileUtils;

public class CreateBigramList {

	public static void main(String... args) throws Throwable {

		Set<String> set = new TreeSet<String>();
		for (File file : FileUtils.getDescendantFilesInFolder("/Users/craigtrim/Desktop/ccr-bigram-filtered/", "*")) {
			for (String line : FileUtils.toList(file, Codepage.UTF_8)) {

				String[] tokens = line.split("\t");
				if (tokens.length > 4)
					set.add(tokens[0] + " " + tokens[1]);
			}
		}

		FileUtils.toFile(set, "/Users/craigtrim/Desktop/ccr-bigrams.txt", Codepage.UTF_8);
	}
}
