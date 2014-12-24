package com.searshc.mygarage.controllers.monitor;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.apis.ncdb.response.vintovehicle.VehiclesListByLicensePlateOrVinNumberResponse;

/**
 * Simple controller with monitor responses
 * @author Carlos Angarita
 *
 */
@RestController
@RequestMapping("monitor")
public class MonitorController {

	private static final Log log = LogFactory.getLog(MonitorController.class);
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public ResponseEntity<String> getMonitorString(@RequestBody final String request) {
		log.info("Request with data: " + request);
		String response = "I am Alive";
		return new ResponseEntity<String>(response, null, HttpStatus.OK);
	}

}
