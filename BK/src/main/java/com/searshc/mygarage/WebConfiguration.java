package com.searshc.mygarage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan
@Import({ApplicationConfiguration.class, DatabaseConfiguration.class})
public class WebConfiguration {

	private static final Log log = LogFactory.getLog(WebConfiguration.class);
}
