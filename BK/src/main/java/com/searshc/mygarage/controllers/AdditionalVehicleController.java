package com.searshc.mygarage.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searshc.mygarage.entities.AdditionalVehicle;
import com.searshc.mygarage.services.AdditionalVehicleService;

@RestController
@RequestMapping("/additionalvehicle")
public class AdditionalVehicleController {
	
	@Inject
	private AdditionalVehicleService additionalVehicleService;
	
	@RequestMapping(value = "vehicles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AdditionalVehicle>> listAdditionalVehicles() {
		List<AdditionalVehicle> response = this.additionalVehicleService.getList();
		return new ResponseEntity<List<AdditionalVehicle>>(response, null, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdditionalVehicle> createAndSaveNewAdditionallVehicle(@RequestParam("make") String make,
			@RequestParam("model") String model,
			@RequestParam("year") int year,
			@RequestParam("color") String color,
			@RequestParam("mileage") int mileage) {
		
		AdditionalVehicle additionalVehicle = this.additionalVehicleService.createAndSaveNewAdditionalVehicle(make, model, year, color, mileage);
		return new ResponseEntity<AdditionalVehicle>(additionalVehicle, null, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{vehicleId}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdditionalVehicle> getAdditionalVehicleDetails(@PathVariable("vehicleId") Integer vehicleId) {
		AdditionalVehicle additionalVehicle = this.additionalVehicleService.getItem(vehicleId);
		return new ResponseEntity<AdditionalVehicle>(additionalVehicle, null, HttpStatus.OK);
	}
}
