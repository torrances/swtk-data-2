package org.swtk.data.gngrams.core.dmo;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.swtk.data.gngrams.core.dto.GngramsLocation;
import org.swtk.data.gngrams.core.util.NgramType;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

@Lazy
@Service
public class GngramFileLocator {

	public static LogManager	logger	= new LogManager(GngramFileLocator.class);

	@Autowired
	GngramsLocation				gngramsLocation;

	private void add(Map<String, Collection<File>> map, String index, File file) {
		Collection<File> inner = (map.containsKey(index)) ? map.get(index) : new TreeSet<File>();
		inner.add(file);
		map.put(index, inner);
	}

	private String getFileLocation(NgramType ngramType) throws BusinessException {
		if (NgramType.UNIGRAM == ngramType) return gngramsLocation.getUnigrams();
		if (NgramType.BIGRAM == ngramType) return gngramsLocation.getBigrams();
		if (NgramType.TRIGRAM == ngramType) return gngramsLocation.getTrigrams();

		throw new AdapterValidationException("NgramType not recognized (value = %s)", ngramType);
	}

	private Collection<File> getFilesForSearching(Map<String, Collection<File>> map, String term) throws BusinessException {

		if (term.length() > 2) {
			String alpha = term.toLowerCase().substring(0, 2);
			if (map.containsKey(alpha)) return map.get(alpha);
		}

		String alpha = term.toLowerCase().substring(0, 1);
		if (map.containsKey(alpha)) return map.get(alpha);

		throw new BusinessException("No Files Found for Term (value = %s)", term);
	}

	/**
	 * 	filenames may look either like this:
	 * 		googlebooks-eng-all-1gram-20120701-j
	 * 	or like this 
	 * 		ooglebooks-eng-all-2gram-20120701-qu
	 * 	this map will index filenames by the characters following the last hyphen
	 * @param files
	 * @return	an indexing map
	 */
	private Map<String, Collection<File>> indexFiles(Collection<File> files) {
		Map<String, Collection<File>> map = new HashMap<String, Collection<File>>();

		for (File file : files) {
			if (file.getAbsolutePath().endsWith(".DS_Store")) continue;

			String name = FileUtils.getName(file);
			if (!name.contains("-")) logger.error("Unexpected File Name format (value = %s)", name);

			String ndx1 = StringUtils.substringAfterLast(name, "-");
			String ndx2 = ndx1.substring(0, 1);

			add(map, ndx1, file);
			add(map, ndx2, file);
		}

		return map;
	}

	public Collection<File> process(String term, NgramType ngramType) throws BusinessException {
		String location = getFileLocation(ngramType);

		Collection<File> files = FileUtils.getDescendantFilesInFolder(location, "*");
		if (files.isEmpty()) throw new AdapterValidationException("No Files Found (location = %s)", location);

		Map<String, Collection<File>> map = indexFiles(files);

		Collection<File> filesForSearching = getFilesForSearching(map, term);
		logger.info("Found Files for Searching (total = %s, term = %s)\n%s", filesForSearching.size(), term, toLogString(filesForSearching));

		return filesForSearching;
	}

	private String toLogString(Collection<File> files) {
		StringBuilder sb = new StringBuilder();

		Iterator<File> iter = files.iterator();
		while (iter.hasNext()) {
			sb.append("\t" + FileUtils.getName(iter.next()));
			if (iter.hasNext()) sb.append("\n");
		}

		return sb.toString();
	}
}
