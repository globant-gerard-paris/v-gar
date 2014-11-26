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

//USE THIS CONFIG IF YOU ARE IN SEARS VPN
/*import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import org.springframework.http.client.SimpleClientHttpRequestFactory;*/

@Component
public class NHTSAApiImpl implements NHTSAApi {

    private static final Log log = LogFactory.getLog(NHTSAApiImpl.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${nhtsa.api.recall.query.template.year.make.model}")
    private String endpoint;

    public NHTSAApiImpl() {
        //USE THIS CONFIG IF YOU ARE IN SEARS VPN
		/*SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

         Proxy proxy= new Proxy(Type.HTTP, new InetSocketAddress("166.76.3.199", 8080));
         requestFactory.setProxy(proxy);

         this.restTemplate = new RestTemplate(requestFactory);*/
        this.restTemplate = new RestTemplate();
    }

    public NHTSARecalls getRecalls(final int modelYear, final String make, final String model) throws NHTSARecallsException {
        ResponseEntity<NHTSARecalls> response;
        String url = String.format(this.endpoint, modelYear, make, model);
        log.info("Querying recalls at: " + url);
        try {
            response = this.restTemplate.getForEntity(url, NHTSARecalls.class);
        } catch (RestClientException e) {
            String message = new StringBuilder()
                    .append("Could not get Recalls for: ")
                    .append(make)
                    .append(" ")
                    .append(model)
                    .append(" ")
                    .append(modelYear).toString();
            log.error(message, e);
            throw new NHTSARecallsException(message);
        }

        log.info("Recalls found: " + response.getBody().getCount());
        return response.getBody();
    }

}
