package com.searshc.mygarage.apis.trends;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searshc.mygarage.apis.trends.response.TrendReader;
import com.searshc.mygarage.exceptions.VehicleTrendException;


@Component
public class TrendReaderApiImpl implements TrendReaderApi {
	
	private static final Log log = LogFactory.getLog(TrendReaderApiImpl.class);
	
	private RestTemplate restTemplate = new RestTemplate();
	private ObjectMapper mapper = new ObjectMapper();

	@Value("${trend.reader.api.query.template.year.make.model}")
	private String endpoint;
	
	
	public TrendReaderApiImpl() {
		this.restTemplate = new RestTemplate();
		this.mapper = new ObjectMapper();
	}
	
	public List<TrendReader> getTrends(final String make, final Integer limit) throws VehicleTrendException {
		TrendReader[] trends = {};
		String responseEntity = "";
		String url = String.format(this.endpoint, make, limit);
		log.info("Querying trends at: " + url);
		try {
			responseEntity = this.restTemplate.getForObject(url, String.class);
			trends = this.mapper.readValue(responseEntity, TrendReader[].class);
		} catch (Exception e) {
			String message = new StringBuilder()
				.append("Could not get Trends for: ")
				.append(make).toString();
			log.error(message, e);
			throw new VehicleTrendException(message);
		}

		log.info("Trends found: " + trends.length);
		return Arrays.asList(trends);
	}	

}
