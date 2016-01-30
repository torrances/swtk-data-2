package org.swtk.data.gngrams.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.swtk.data.gngrams.core.dto.GngramsLocation;

import com.trimc.blogger.commons.LogManager;

@Configuration
@ComponentScan("org.swtk.data.gngrams")
public class SpringConfig {

	public static LogManager logger = new LogManager(SpringConfig.class);

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@Qualifier("gngramsLocation")
	public GngramsLocation getGngramsLocation() {
		return new GngramsLocation();
	}
}
