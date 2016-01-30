package org.swtk.data.twitter.core.dto.local;

import org.swtk.data.twitter.core.dto.type.AgeRange;
import org.swtk.data.twitter.core.dto.type.Gender;

import com.google.gson.annotations.Expose;

public class LocalIdentity {

	@Expose
	private AgeRange	ageRange;

	@Expose
	private Gender		gender;

	@Expose
	private String		handle;

	@Expose
	private String		name;

	public AgeRange getAgeRange() {
		return ageRange;
	}

	public Gender getGender() {
		return gender;
	}

	public String getHandle() {
		return handle;
	}

	public String getName() {
		return name;
	}

	public void setAgeRange(AgeRange ageRange) {
		this.ageRange = ageRange;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public void setName(String name) {
		this.name = name;
	}
}
