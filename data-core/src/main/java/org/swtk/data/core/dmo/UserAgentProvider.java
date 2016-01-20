package org.swtk.data.core.dmo;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import com.trimc.blogger.commons.LogManager;
import com.trimc.blogger.commons.exception.BusinessException;
import com.trimc.blogger.commons.type.Codepage;
import com.trimc.blogger.commons.utils.MathUtils;
import com.trimc.blogger.commons.utils.file.FileUtils;

public class UserAgentProvider {

	private static final String PATH = "../data-core/src/main/resources/user-agents.txt";

	private static final File SOURCE = new File(PATH);

	private static UserAgentProvider instance;

	public static LogManager logger = new LogManager(UserAgentProvider.class);

	public static UserAgentProvider getInstance() {
		if (null == instance) instance = new UserAgentProvider();
		return instance;
	}

	private String[] userAgents;

	private UserAgentProvider() {}

	public String[] all() {
		if (null == userAgents) {
			try {

				Set<String> set = new TreeSet<String>();
				for (String userAgent : FileUtils.toList(SOURCE, Codepage.UTF_8))
					set.add(userAgent.trim());

				this.userAgents = (String[]) set.toArray(new String[set.size()]);

			} catch (BusinessException e) {}
		}

		return this.userAgents;
	}

	public String random() {
		return all()[MathUtils.random(0, all().length - 1)];
	}
}
