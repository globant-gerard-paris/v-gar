package com.searshc.mygarage.apis.trends;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.searshc.mygarage.apis.trends.response.TrendReader;
import com.searshc.mygarage.exceptions.VehicleTrendException;


@Component
public class TrendReaderApiImpl implements TrendReaderApi {
	
	private static final Log log = LogFactory.getLog(TrendReaderApiImpl.class);
	
	private RestTemplate restTemplate = new RestTemplate();

	@Value("${trend.reader.api.query.template.year.make.model}")
	private String endpoint;
	
	
	public TrendReaderApiImpl() {
		//USE THIS CONFIG IF YOU ARE IN SEARS VPN
		/*SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

	    Proxy proxy= new Proxy(Type.HTTP, new InetSocketAddress("166.76.3.199", 8080));
	    requestFactory.setProxy(proxy);

	    this.restTemplate = new RestTemplate(requestFactory);*/
		this.restTemplate = new RestTemplate();
	}
	
	@SuppressWarnings("unchecked")
	public List<TrendReader> getTrends(final String make) throws VehicleTrendException {
		ResponseEntity<TrendReader[]> responseEntity = null;
		String url = String.format(this.endpoint, make);
		log.info("Querying trends at: " + url);
		try {
			responseEntity = restTemplate.getForEntity(url, TrendReader[].class);
		} catch (RestClientException e) {
			String message = new StringBuilder()
				.append("Could not get Trends for: ")
				.append(make).toString();
			log.error(message, e);
			throw new VehicleTrendException(message);
		}

		log.info("Trends found: " + responseEntity.getBody().length);
		return Arrays.asList(responseEntity.getBody());
	}
	
}
