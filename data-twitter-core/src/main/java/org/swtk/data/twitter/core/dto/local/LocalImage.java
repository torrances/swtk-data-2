package org.swtk.data.twitter.core.dto.local;

import com.google.gson.annotations.Expose;

/**
 * 	Purpose:
 * 	1.	represents a local image embedded in a tweet
 * 
 * 	Sample Format:
 * 		<img 
 * 			class="Emoji Emoji--forText" 
 * 			src="Canela%20Trigueros%20%28@canelatrigueros%29%20%7C%20Twitter_files/1f493.png" 
 * 			draggable="false" 
 * 			alt="alt text" 
 * 			title="Beating heart" 
 * 			aria-label="Emoji: Beating heart">
 */
public class LocalImage {

	@Expose
	private String	altText;

	@Expose
	private String	ariaLabel;

	@Expose
	private String	imgClass;

	@Expose
	private String	src;

	@Expose
	private String	title;

	public String getAltText() {
		return altText;
	}

	public String getAriaLabel() {
		return ariaLabel;
	}

	public String getImgClass() {
		return imgClass;
	}

	public String getSrc() {
		return src;
	}

	public String getTitle() {
		return title;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public void setAriaLabel(String ariaLabel) {
		this.ariaLabel = ariaLabel;
	}

	public void setImgClass(String imgClass) {
		this.imgClass = imgClass;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
