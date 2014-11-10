package com.searshc.mygarage.apis.nhtsa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.searshc.mygarage.apis.nhtsa.response.NHTSARecalls;
import com.searshc.mygarage.exceptions.NHTSARecallsException;


@Component
public class NHTSAApiImpl implements NHTSAApi {
	
	private static final Log log = LogFactory.getLog(NHTSAApiImpl.class);
	
	private RestTemplate restTemplate = new RestTemplate();

	@Value("${nhtsa.api.recall.query.tamplate.year.make.model}")
	private String endpoint;
	
	public NHTSARecalls getRecalls(final int modelYear, final String make, final String model) throws NHTSARecallsException {
		ResponseEntity<NHTSARecalls> response;
		String url = String.format(this.endpoint, modelYear, make, model);
		log.debug("Querying recalls at: " + url);
		try {
			response = this.restTemplate.getForEntity(url, NHTSARecalls.class);
		}
		catch (RestClientException e) {
			throw new NHTSARecallsException("Could not get Recalls for: " + make + " " + model + " " + modelYear);
		}

		log.debug("Recalls found: " + response.getBody().getCount());
		return response.getBody();
	}
	

}
