package org.swtk.data.gngrams.parse;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.swtk.data.gngrams.core.SpringConfig;
import org.swtk.data.gngrams.core.util.NgramType;
import org.swtk.data.gngrams.parse.dmo.GngramsParser;

public class GngramParserTest {

	private GngramsParser getGngramsParser() {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		assertNotNull(ctx);

		GngramsParser gngramParser = (GngramsParser) ctx.getBean("gngramsParser");
		assertNotNull(gngramParser);

		return gngramParser;
	}

	@Test
	public void faith() throws Throwable {
		getGngramsParser().process("faith", NgramType.TRIGRAM);
	}
}
