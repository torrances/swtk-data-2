package org.swtk.data.gngrams.parse.dmo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.swtk.data.gngrams.core.dmo.GngramFileLocator;
import org.swtk.data.gngrams.core.dto.Gngram;
import org.swtk.data.gngrams.core.dto.adapter.GngramAdapter;
import org.swtk.data.gngrams.core.util.NgramType;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.AdapterValidationException;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.Stopwatch;
import com.trimc.blogger.commons.utils.TextUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;
import com.trimc.blogger.commons.utils.string.StringUtils;

import redis.clients.jedis.Jedis;

/**
 * 	@author craig
 * 	
 * 	Prerequisites:
 * 	1.	Redis
 * 		sudo apt-get install -y redis-server	(Ubuntu/Debian)
 * 		brew install redis						(OS X)
 * 	2.	once Redis is installed, open a new terminal window and type
 * 		"redis-server"
 * 		
 * 		The output from a succcessful start should look something like this:
 * 
			1851:C 29 Jan 22:20:33.123 # Warning: no config file specified, using the default config. In order to specify a config file use redis-server /path/to/redis.con
			1851:M 29 Jan 22:20:33.125 * Increased maximum number of open files to 10032 (it was originally set to 256).                _._                                                  
			           _.-``__ ''-._                                             
			      _.-``    `.  `_.  ''-._           Redis 3.0.7 (00000000/0) 64 bit
			  .-`` .-```.  ```\/    _.,_ ''-._                                   
			 (    '      ,       .-`  | `,    )     Running in standalone mode
			 |`-._`-...-` __...-.``-._|'` _.-'|     Port: 6379
			 |    `-._   `._    /     _.-'    |     PID: 1851
			  `-._    `-._  `-./  _.-'    _.-'                                   
			 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
			 |    `-._`-._        _.-'_.-'    |           http://redis.io        
			  `-._    `-._`-.__.-'_.-'    _.-'                                   
			 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
			 |    `-._`-._        _.-'_.-'    |                                  
			  `-._    `-._`-.__.-'_.-'    _.-'                                   
			      `-._    `-.__.-'    _.-'                                       
			          `-._        _.-'                                           
			              `-.__.-'                                               
			
			1851:M 29 Jan 22:20:33.128 # Server started, Redis version 3.0.7
			1851:M 29 Jan 22:20:33.128 * The server is now ready to accept connections on port 6379
 *
 */
@Lazy
@Service
public class GngramsParser {

	private static final int	GNGRAM_PROCESS_THRESHOLD	= 100;

	public static LogManager	logger						= new LogManager(GngramsParser.class);

	@Autowired
	GngramFileLocator			gngramFileLocator;

	Jedis						jedis;

	int							totalGngramsProcessed		= 0;

	private boolean contains(String line, String term) {
		if (!line.contains(term)) return false;

		if (line.contains(term + " ")) return true;
		if (line.contains(" " + term)) return true;

		return false;
	}

	private void process(List<Gngram> gngrams) {
		for (Gngram gngram : gngrams) {
			String key = gngram.getTerm();

			String value = jedis.get(key);
			Double total = (null == value) ? 0d : Double.parseDouble(value);
			total += gngram.getMatchCount();

			jedis.set(key, String.valueOf(total));
		}
	}

	// TODO: should have a contract with terms, ngram-type, file-save-loc, threshold
	public void process(String term, NgramType ngramType) throws BusinessException {

		jedis = new Jedis("localhost");
		jedis.connect();
		final String PATTERN = String.format("*%s*", term);

		if (!StringUtils.hasValue(term)) throw new AdapterValidationException("Invalid Input: Must supply a term to search by");

		String alpha1 = term.toLowerCase().substring(0, 1);
		if (!TextUtils.isAlpha(alpha1)) throw new AdapterValidationException("Invalid Input: Must supply an alpha-term to search by");

		/* 	file format
		 * 		Facilities_NOUN by_ADP Type_NOUN	1970	2	2
		 */
		for (File file : gngramFileLocator.process(term, ngramType)) {

			Stopwatch timer = new Stopwatch();
			List<Gngram> gngrams = new ArrayList<Gngram>();

			try {

				try (FileInputStream fis = new FileInputStream(file)) {

					try (BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

						String line = reader.readLine().toLowerCase();
						while (null != line) {

							if (contains(line, term)) {
								Gngram gngram = GngramAdapter.transform(line);
								if (GngramAdapter.isAlphaOnly(gngram)) gngrams.add(gngram);
							}

							if (0 == gngrams.size() % GNGRAM_PROCESS_THRESHOLD) {
								totalGngramsProcessed += gngrams.size();
								process(gngrams);
								gngrams = new ArrayList<Gngram>();

								if (jedis.keys(PATTERN).size() > 1 && totalGngramsProcessed > 0) {
									logger.info("Processed Gngrams (gngrams-processed = %s, unique = %s, file-time = %s)", /*			*/StringUtils.format(totalGngramsProcessed), /*			*/StringUtils.format(jedis.keys(PATTERN).size()), /*			*/timer.getTotalTime());
									write(term, PATTERN);
								}
							}

							line = reader.readLine().toLowerCase();
						}
					}
				}

			} catch (IOException e) {
				logger.error(e);
			}

			logger.info("Located Gngrams for Term (term = %s, total = %s, file = %s, time = %s)", term, StringUtils.format(gngrams.size()), file.getName(), timer.getTotalTime());
		}

		jedis.close();
	}

	private void write(String term, final String PATTERN) throws BusinessException {
		StringBuilder sb = new StringBuilder();

		for (String key : jedis.keys(PATTERN))
			sb.append(key + "\t" + jedis.get(key) + "\n");

		FileUtils.toFile(sb, "/Users/craigtrim/data/Data/GG-" + term + ".dat", Codepage.UTF_8);
	}
}
