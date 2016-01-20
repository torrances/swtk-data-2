package org.swtk.data.core;

import org.junit.Test;
import org.swtk.data.core.dmo.UserAgentProvider;
import static org.junit.Assert.*;

public final class UserAgentProviderTest {

	@Test
	public void test() throws Throwable {

		boolean here = false;
		for (int i = 0; i < 5000; i++) {
			try {
				UserAgentProvider.getInstance().random();
			} catch (Exception e) {
				here = true;
			}
		}

		assertFalse(here);
	}
}
