package org.swtk.data.rt.core.dto.adapter;

import org.swtk.data.rt.core.dto.Critic;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class CriticAdapter {

	public static String toString(Critic critic) {
		StringBuilder sb = new StringBuilder();

		sb.append("name = " + critic.getName());
		sb.append(", company = " + critic.getCompany());

		return sb.toString();
	}

	public static Critic transform(String name, String company) throws AdapterValidationException {
		Critic critic = new Critic();
		
		critic.setName(name);
		critic.setCompany(company);
		
		return critic;
	}
}
