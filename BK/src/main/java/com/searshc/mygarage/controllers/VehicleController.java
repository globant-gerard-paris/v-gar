package com.searshc.mygarage.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searshc.mygarage.dtos.VehicleConfirmationDTO;
import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.UserInformation;
import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.entities.recalls.VehicleRecalls;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.exceptions.NHTSARecallsException;
import com.searshc.mygarage.exceptions.UserNotFoundException;
import com.searshc.mygarage.services.NcdbService;
import com.searshc.mygarage.services.UserVehicleService;
import com.searshc.mygarage.services.nhtsa.VehicleRecallsService;
import com.searshc.mygarage.services.user.UserInformationService;
import com.searshc.mygarage.services.vehicle.VehicleServiceImpl;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	private static final Log log = LogFactory.getLog(VehicleController.class);
	
    private NcdbService ncdbService;
    private VehicleRecallsService nhtsaService;
    private VehicleServiceImpl vehicleService;
    private UserVehicleService userVehicleService;
    private UserInformationService userInformationService;
    private ObjectMapper objectMapper;

    @Inject
    public VehicleController(final NcdbService ncdbService, final VehicleRecallsService nhtsaService,
    		final VehicleServiceImpl vehicleService, final UserVehicleService userVehicleService, final UserInformationService userInformationService,
    		final ObjectMapper objectMapper) {
        this.ncdbService = Validate.notNull(ncdbService, "The NCDB Service cannot be null");
        this.nhtsaService = Validate.notNull(nhtsaService, "The NHTSA Service cannot be null");
        this.vehicleService = Validate.notNull(vehicleService, "The Vehicle Service cannot be null");
        this.userVehicleService = Validate.notNull(userVehicleService, "The UserVehicle Service cannot be null");
        this.userInformationService = Validate.notNull(userInformationService, "The UserInformation Service cannot be null");
        this.objectMapper = Validate.notNull(objectMapper, "The ObjectMapper cannot be null");
    }

    @RequestMapping(value = "/family/{ncdbId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Vehicle>> getVehicles(@PathVariable("ncdbId") Integer ncdbId) throws NCDBApiException {
        List<Vehicle> vehicles = this.ncdbService.listVehicles(ncdbId);
        return new ResponseEntity<List<Vehicle>>(vehicles, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicles/{ncdbId}/transactions/{vehicleId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Order>> getCarTransactionsHistory(@PathVariable("ncdbId") Integer ncdbId,
            @PathVariable("vehicleId") Integer vehicleId) {
        List<Order> orders = this.ncdbService.getTransactions(ncdbId, vehicleId);
        return new ResponseEntity<List<Order>>(orders, null, HttpStatus.OK);
    }
    
    @RequestMapping("/recalls/year/{year}/make/{make}/model/{model}/order/{order}")
    @ResponseBody
    public ResponseEntity<List<VehicleRecalls>> getRecalls(@PathVariable("year") int year,
    		@PathVariable("make") String make,
    		@PathVariable("model") String model,
    		@PathVariable("order") String order) throws NHTSARecallsException {
    	boolean isAscendingOrder;
    	
    	if(order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("ascending")) {
    		isAscendingOrder= true;
    	} else if(order.equalsIgnoreCase("desc") || order.equalsIgnoreCase("descending")) {
    		isAscendingOrder = false;
    	} else {
    		throw new IllegalArgumentException("Invalid order. The Order must be ASC or DESC");
    	}
    	
    	List<VehicleRecalls> response = this.nhtsaService.getRecallsOrderedByDate(year, make, model, isAscendingOrder);
    	return new ResponseEntity<List<VehicleRecalls>>(response, null, HttpStatus.OK);
    }
    
    @RequestMapping("/recalls/year/{year}/make/{make}/model/{model}/last")
    @ResponseBody
    public ResponseEntity<VehicleRecalls> getRecalls(@PathVariable("year") int year,
    		@PathVariable("make") String make,
    		@PathVariable("model") String model) throws NHTSARecallsException {
    	
    	VehicleRecalls response = this.nhtsaService.getLastRecall(year, make, model);
    	return new ResponseEntity<VehicleRecalls>(response, null, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/vehicles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vehicle>> listAdditionalVehicles() {
		List<Vehicle> response = this.vehicleService.getList();
		return new ResponseEntity<List<Vehicle>>(response, null, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> createAndSaveNewVehicle(
			@RequestParam("familyId") Long familyId,
			@RequestParam("tangibleId") Long tangibleId,
			@RequestParam("make") String make,
			@RequestParam("model") String model,
			@RequestParam("year") int year,
			@RequestParam("color") String color,
			@RequestParam("mileage") int mileage) {
		
		Vehicle vehicle = this.vehicleService.createAndSaveNewVehicle(familyId, tangibleId, make, model, year, color, mileage);
		return new ResponseEntity<Vehicle>(vehicle, null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{vehicleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> getAdditionalVehicleDetails(@PathVariable("vehicleId") Integer vehicleId) {
		Vehicle vehicle = this.vehicleService.getItem(vehicleId);
		return new ResponseEntity<Vehicle>(vehicle, null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicles/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Set<VehicleConfirmationDTO>> getVehiclesToConfirm(@PathVariable("userId") long userId) throws NCDBApiException {
		UserInformation user = this.userInformationService.getItem(userId);
		Set<VehicleConfirmationDTO> result = new HashSet<VehicleConfirmationDTO>();
		List<Vehicle> localVehicles = this.vehicleService.getVehiclesByUserId(user.getId());
		result.addAll(this.userVehicleService.convert(localVehicles, true));
		if(user.getFamilyId() != null) {
			List<Vehicle> ncdbVehicles = this.ncdbService.listVehicles(user.getFamilyId().intValue());
			ncdbVehicles.removeAll(localVehicles);
			result.addAll(this.userVehicleService.convert(ncdbVehicles, false));
		} 
		return new ResponseEntity<Set<VehicleConfirmationDTO>>(result, null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicles/confirm/user/{userId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> updateVehicleConfirmation(@PathVariable("userId") Long userId, @RequestBody String jsonBody) throws IOException, UserNotFoundException {
		VehicleConfirmationDTO[] vehicleConfirmationDTOs = objectMapper.readValue(jsonBody, VehicleConfirmationDTO[].class);
		UserInformation userInformation = this.userInformationService.getItem(userId);
		if(userInformation == null) {
			throw new UserNotFoundException("User not found with id: " + userId);
		}

		int recordsDeleted = this.userVehicleService.deleteUserVehiclesByUserId(userId);
		log.info(recordsDeleted + " UserVehicle records deleted for userId " + userId);
		List<VehicleConfirmationDTO> vehicleDtoList = this.userVehicleService.discardUnconfirmed(vehicleConfirmationDTOs);

		Set<UserVehicle> userVehicles = this.userVehicleService.convert(vehicleDtoList, userInformation);
		Set<Vehicle> newVehicles = this.userVehicleService.extractNoPersistedVehicles(userVehicles);
		if(newVehicles.size() > 0) {
			this.vehicleService.saveAndFlush(newVehicles);
		}
		userVehicles = this.userVehicleService.saveAndFlush(userVehicles);
		log.info(userVehicles.size() + " vehicles were confirmed");
		return new ResponseEntity<Object>(null, null, HttpStatus.OK);
	}
    
	

}
