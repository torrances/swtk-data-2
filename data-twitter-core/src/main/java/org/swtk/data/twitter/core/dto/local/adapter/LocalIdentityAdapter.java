package org.swtk.data.twitter.core.dto.local.adapter;

import org.swtk.data.twitter.core.dto.local.LocalIdentity;
import org.swtk.data.twitter.core.dto.type.AgeRange;
import org.swtk.data.twitter.core.dto.type.Gender;

import com.trimc.blogger.commons.exception.AdapterValidationException;

public final class LocalIdentityAdapter {

	public static LocalIdentity transform(String name, String handle, AgeRange ageRange, Gender gender) throws AdapterValidationException {
		LocalIdentity bean = new LocalIdentity();

		bean.setName(name);
		bean.setHandle(handle);
		bean.setAgeRange(ageRange);
		bean.setGender(gender);

		return bean;
	}
}
