package com.searshc.mygarage.controllers.landing;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searshc.mygarage.apis.syw.SYWApiImpl;
import com.searshc.mygarage.apis.syw.response.SYWUserResponse;
import com.searshc.mygarage.exceptions.SYWServiceException;
import com.searshc.mygarage.services.landing.LandingService;
import com.searshc.mygarage.services.landing.UserVehicleStatus;

/**
 * 
 * The {@link LandingController} have the responsibility to retrieve all the information necessary
 * to resolve landing Page.
 * 
 * @author Jero
 *
 */
@RestController
@RequestMapping("/landing")
public class LandingController {

	private LandingService landingService;

	/**
	 * @param userInformationService
	 */
	@Inject
	public LandingController(LandingService landingService) {
		this.landingService = Validate
				.notNull(landingService, "The landing service cannot be null");
	}

	/**
	 * Given a {@code token} that will return the information of the token related of NCDB, SYW and
	 * Virtual Garage information.
	 * 
	 * @param token
	 * @return
	 * @throws SYWServiceException 
	 */
	@RequestMapping(value = "/token/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UserVehicleStatus> getSessionUser(@PathVariable("token") String token) throws SYWServiceException {
		UserVehicleStatus userVehicleStatus = this.landingService.getUserVehicleStatus(token);
		if (userVehicleStatus == null) {
			return new ResponseEntity<UserVehicleStatus>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserVehicleStatus>(userVehicleStatus, HttpStatus.OK);
	}
}