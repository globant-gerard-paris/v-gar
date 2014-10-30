package com.searshc.mygarage;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

@Configuration
@EnableAutoConfiguration
@PropertySource(value={"file:${spring.config.location}application-${spring.profiles.active}.properties"})
public class ApplicationConfiguration {

	private static final Log log = LogFactory.getLog(ApplicationConfiguration.class);
	
	@Inject
	private Environment env;
	
	@Value("${spring.config.location}")
	private String springConfigLocation;
	
	
}
