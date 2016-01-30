package org.swtk.data.twitter.core.dto.local;

import com.google.gson.annotations.Expose;

/**
 * 	Purpose:
 * 	1.	represents a local link
 * 
 * 	Sample format;
 * 	<a 
 * 		href="https://t.co/ULSBV1BLDQ" 
 * 		class="twitter-timeline-link u-hidden" 
 * 		data-pre-embedded="true" 
 * 		dir="ltr">
 * 			pic.twitter.com/ULSBV1BLDQ
 * 	</a>
 *
 */
public class LocalLink {

	@Expose
	private String href;
	
	@Expose
	private String text;

	public String getHref() {
		return href;
	}

	public String getText() {
		return text;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setText(String text) {
		this.text = text;
	}	
}
