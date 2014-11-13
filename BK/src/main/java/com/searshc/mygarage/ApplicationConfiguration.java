package com.searshc.mygarage;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableAutoConfiguration
@PropertySource(value={"file:${spring.config.location}application-${spring.profiles.active}.properties"})
public class ApplicationConfiguration {

	private static final Log log = LogFactory.getLog(ApplicationConfiguration.class);
	
	@Inject
	private Environment env;
	
	@Value("${spring.config.location}")
	private String springConfigLocation;
	
	@Bean
	public ObjectMapper getObjectMapper() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		// Enable Serialization features
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		objectMapper.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, true);
		objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
		
		// Disable Serialization features
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, false);
		
		// Enable Deserialization features
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, true);
		
		// Disable Deserialization features
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);
		
		return objectMapper;
	}
}
