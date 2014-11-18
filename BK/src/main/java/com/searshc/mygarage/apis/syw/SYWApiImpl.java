package com.searshc.mygarage.apis.syw;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.searshc.mygarage.apis.syw.response.SYWUserResponse;
import com.searshc.mygarage.exceptions.SYWServiceException;

/**
 * @author Jero
 */
@Service
public class SYWApiImpl implements SYWApi {

	private static final Log log = LogFactory.getLog(SYWApiImpl.class);

	private RestTemplate restTemplate;

	@Value("${syw.conf.app.secret}")
	private String appSecret;

	@Value("${syw.api.url.get.user}")
	private String currentUserEndpoint;

	
	public SYWApiImpl() {
//		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//	    Proxy proxy= new Proxy(Type.HTTP, new InetSocketAddress("166.76.3.199", 8080));
//	    requestFactory.setProxy(proxy);
//	    this.restTemplate = new RestTemplate(requestFactory);
	    this.restTemplate = new RestTemplate();
	}

	/**
	 * Given {@code token} we will search all information against SYW services. correct next path.
	 * 
	 * @param token
	 * @return
	 */
	public SYWUserResponse getUserInfoByToken(String token) throws SYWServiceException {
		Validate.notNull(token, "Could not be null the token.");

		String hash = getHash(token);
		String url = String.format(this.currentUserEndpoint, token, hash);
		log.info("Querying get syw user at: " + url);

		ResponseEntity<SYWUserResponse> response;
		try {
			response = this.restTemplate.getForEntity(url, SYWUserResponse.class);
		} catch (RestClientException e) {
			String message = new StringBuilder().append("Could not get user of SYW given token: ")
					.append(token).toString();
			log.error(message, e);
			throw new SYWServiceException(message);
		}

		return response.getBody();
	}

	/**
	 * Given a {@code token} return the generate SYW Hash.
	 * 
	 * @param token
	 * @return
	 * @throws SYWServiceException
	 */
	private String getHash(final String token) throws SYWServiceException {
		try {
			return SYWHash.getHash(appSecret, token);
		} catch (Exception e) {
			throw new SYWServiceException("Could not generate HashCode from token: " + token);
		}
	}
}
