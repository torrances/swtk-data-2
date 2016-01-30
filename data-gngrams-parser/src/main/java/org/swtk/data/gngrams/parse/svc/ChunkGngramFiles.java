package org.swtk.data.gngrams.parse.svc;

import org.swtk.common.framework.type.Alpha;
import org.swtk.data.gngrams.parse.dmo.GngramFileChunker;

public class ChunkGngramFiles {

	public static void main(String... args) throws Throwable {
		for (Alpha alpha : Alpha.values())
			new GngramFileChunker().process(alpha);
	}
}
