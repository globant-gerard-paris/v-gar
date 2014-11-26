package com.searshc.mygarage.controllers.home;

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

import com.searshc.mygarage.dtos.home.HomeDto;
import com.searshc.mygarage.services.vehicle.FamilyVehicleService;

/**
 *
 * The {@link HomeController} have the responsibility to manager the request about the base
 * information of the application.
 *
 * @author Jero
 *
 */
@RestController
@RequestMapping("/home")
public class HomeController {

	private FamilyVehicleService familyVehicleService;

	/**
	 * @param userService
	 */
	@Inject
	public HomeController(FamilyVehicleService familyVehicleService) {
		this.familyVehicleService = Validate.notNull(familyVehicleService,
				"The User Vehicle Service cannot be null");
	}

	@RequestMapping(value = "/user/token/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<HomeDto> getUser(@PathVariable("token") String token) {
		HomeDto homeInformation = this.familyVehicleService.getHomeInformation(token);
		return new ResponseEntity<HomeDto>(homeInformation, HttpStatus.OK);
	}
}
