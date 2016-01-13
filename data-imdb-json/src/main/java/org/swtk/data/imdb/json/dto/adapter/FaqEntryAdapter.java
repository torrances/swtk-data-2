package org.swtk.data.imdb.json.dto.adapter;

import java.util.Collection;
import java.util.Iterator;

import org.swtk.data.imdb.json.dto.FaqEntry;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class FaqEntryAdapter {

	public static String toTsv(Collection<FaqEntry> faqEntries) {
		StringBuilder sb = new StringBuilder();
		
		Iterator<FaqEntry> iter = faqEntries.iterator();
		while (iter.hasNext()) {
			sb.append(toTsv(iter.next()));
			if (iter.hasNext()) sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
	
	public static String toTsv(FaqEntry faqEntry) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(faqEntry.getId());
		sb.append("\t");
		sb.append(faqEntry.getQuestion());
		sb.append("\t");
		sb.append(faqEntry.getAnswer());
		
		return sb.toString();
	}
	
	public static FaqEntry transform(String id, String question, String answer) throws AdapterValidationException {
		FaqEntry faqEntry = new FaqEntry();

		faqEntry.setId(id);
		faqEntry.setQuestion(question);
		faqEntry.setAnswer(answer);

		return faqEntry;
	}
}
