package com.searshc.mygarage;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import({ApplicationConfiguration.class, DatabaseConfiguration.class})
public class WebConfiguration extends WebMvcConfigurerAdapter {

}
