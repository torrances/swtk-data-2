package org.swtk.data.rt.core.dto;

import com.google.gson.annotations.Expose;
import com.trimc.blogger.commons.utils.string.StringUtils;

public class Critic {
	
	@Expose
	private String name;
	
	@Expose
	private String company;

	public String getCompany() {
		return company;
	}
	
	public String getName() {
		return name;
	}

	public boolean hasCompany() {
		return StringUtils.hasValue(getCompany());
	}

	public boolean hasName() {
		return StringUtils.hasValue(getName());
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setName(String name) {
		this.name = name;
	}
}
