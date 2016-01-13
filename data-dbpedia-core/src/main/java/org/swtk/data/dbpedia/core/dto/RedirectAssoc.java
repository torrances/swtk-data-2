package org.swtk.data.dbpedia.core.dto;

import java.util.Collection;

public class RedirectAssoc {

	private String cannonical;
	
	private Collection<String> variations;

	public String getCannonical() {
		return cannonical;
	}

	public Collection<String> getVariations() {
		return variations;
	}

	public void setCannonical(String cannonical) {
		this.cannonical = cannonical;
	}

	public void setVariations(Collection<String> variations) {
		this.variations = variations;
	}
}
