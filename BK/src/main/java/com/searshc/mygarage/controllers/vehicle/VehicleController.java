package com.searshc.mygarage.controllers.vehicle;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

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
import com.searshc.mygarage.dtos.StoreInfoAndFamilyVehiclesDTO;
import com.searshc.mygarage.dtos.VehicleConfirmationDTO;
import com.searshc.mygarage.dtos.VehicleGenericDescriptionDTO;
<<<<<<< HEAD
import com.searshc.mygarage.dtos.manualvehicle.AddOrUpdateManualFamilyVehicleDTO;
=======
import com.searshc.mygarage.dtos.familyvehicle.AddNewManualFamilyVehicleDTO;
>>>>>>> 8862bd4... merge all
import com.searshc.mygarage.entities.ConfirmedVehicle;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.recalls.VehicleRecalls;
import com.searshc.mygarage.entities.trends.VehicleTrend;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.exceptions.NHTSARecallsException;
import com.searshc.mygarage.exceptions.UserNotFoundException;
import com.searshc.mygarage.exceptions.VehicleTrendException;
import com.searshc.mygarage.orchestrators.AddNewManualFamilyVehicleOrchestrator;
import com.searshc.mygarage.orchestrators.DashboardOrchestrator;
import com.searshc.mygarage.orchestrators.VehicleConfirmationOrchestrator;
import com.searshc.mygarage.services.ncdb.NcdbService;
import com.searshc.mygarage.services.nhtsa.VehicleRecallsService;
import com.searshc.mygarage.services.record.RecordService;
import com.searshc.mygarage.services.trends.VehicleTrendService;
import com.searshc.mygarage.services.user.UserService;
import com.searshc.mygarage.services.vehicle.ConfirmedVehicleService;
import com.searshc.mygarage.services.vehicle.FamilyVehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	private static final Log log = LogFactory.getLog(VehicleController.class);

	private NcdbService ncdbService;
	private RecordService recordService;
	private VehicleRecallsService nhtsaService;
	private FamilyVehicleService familyVehicleService;
	private ConfirmedVehicleService confirmedVehicleService;
	private UserService userService;
	private ObjectMapper objectMapper;
	private DashboardOrchestrator dashboardOrchestrator;
    private VehicleTrendService vehicleTrendService;
    private AddNewManualFamilyVehicleOrchestrator addNewManualFamilyVehicleOrchestrator;
    private VehicleConfirmationOrchestrator vehicleConfirmationOrchestrator;

	@Inject
	public VehicleController(final NcdbService ncdbService,
			final VehicleRecallsService nhtsaService,
			final FamilyVehicleService vehicleService,
			final RecordService recordService,
			final ConfirmedVehicleService confirmedVehicleService,
			final UserService userService, final ObjectMapper objectMapper,
			final DashboardOrchestrator dashboardOrchestrator,
			final VehicleTrendService vehicleTrendService,
			final AddNewManualFamilyVehicleOrchestrator addNewManualFamilyVehicleOrchestrator,
			final VehicleConfirmationOrchestrator vehicleConfirmationOrchestrator) {
		this.ncdbService = notNull(ncdbService,
				"The NCDB Service cannot be null");
		this.recordService = notNull(recordService,
				"The Record Service cannot be null");
		this.nhtsaService = notNull(nhtsaService,
				"The NHTSA Service cannot be null");
		this.familyVehicleService = notNull(vehicleService,
				"The Vehicle Service cannot be null");
		this.confirmedVehicleService = notNull(confirmedVehicleService,
				"The ConfirmedVehicle Service cannot be null");
		this.userService = notNull(userService,
				"The UserInformation Service cannot be null");
		this.objectMapper = notNull(objectMapper,
				"The ObjectMapper cannot be null");
		this.dashboardOrchestrator = notNull(dashboardOrchestrator);
		this.vehicleTrendService = notNull(vehicleTrendService,
				"The Vehicle Trend Service cannot be null");	
		this.addNewManualFamilyVehicleOrchestrator = notNull(addNewManualFamilyVehicleOrchestrator,
				"The AddNewManualFamilyVehicleOrchestrator cannot be null");
		this.vehicleConfirmationOrchestrator = notNull(vehicleConfirmationOrchestrator,
				"The VehicleConfirmationOrchestrator cannot be null");
	}

	@RequestMapping(value = "/family/{familyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<FamilyVehicle>> getVehicles(
			@PathVariable("familyId") Long familyId) throws NCDBApiException {
		List<FamilyVehicle> familyVehicles = this.ncdbService
				.listVehicles(familyId);
		return new ResponseEntity<List<FamilyVehicle>>(familyVehicles, null,
				HttpStatus.OK);
	}

	@RequestMapping("/recalls/year/{year}/make/{make}/model/{model}/order/{order}")
	@ResponseBody
	public ResponseEntity<List<VehicleRecalls>> getRecalls(
			@PathVariable("year") int year, @PathVariable("make") String make,
			@PathVariable("model") String model,
			@PathVariable("order") String order) throws NHTSARecallsException {
		boolean isAscendingOrder;

		if (order.equalsIgnoreCase("asc")
				|| order.equalsIgnoreCase("ascending")) {
			isAscendingOrder = true;
		} else if (order.equalsIgnoreCase("desc")
				|| order.equalsIgnoreCase("descending")) {
			isAscendingOrder = false;
		} else {
			throw new IllegalArgumentException(
					"Invalid order. The Order must be ASC or DESC");
		}

		List<VehicleRecalls> response = this.nhtsaService
				.getRecallsOrderedByDate(year, make, model, isAscendingOrder);
		return new ResponseEntity<List<VehicleRecalls>>(response, null,
				HttpStatus.OK);
	}

	@RequestMapping("/recalls/year/{year}/make/{make}/model/{model}/last")
	@ResponseBody
	public ResponseEntity<VehicleRecalls> getRecalls(
			@PathVariable("year") int year, @PathVariable("make") String make,
			@PathVariable("model") String model) throws NHTSARecallsException {

		VehicleRecalls response = this.nhtsaService.getLastRecall(year, make,
				model);
		return new ResponseEntity<VehicleRecalls>(response, null, HttpStatus.OK);
	}

	@RequestMapping(value = "/familyvehicles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FamilyVehicle>> listAdditionalVehicles() {
		List<FamilyVehicle> response = this.familyVehicleService.getList();
		return new ResponseEntity<List<FamilyVehicle>>(response, null,
				HttpStatus.OK);
	}

<<<<<<< HEAD
	@RequestMapping(value = "/manualvehicle/user/{userId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<FamilyVehicle> addNewManualFamilyVehicle(@PathVariable("userId") final long userId,
			@RequestBody AddOrUpdateManualFamilyVehicleDTO addOrUpdateManualFamilyVehicleDTO) {

		FamilyVehicle familyVehicle = this.addNewManualFamilyVehicleOrchestrator.addNewManualFamilyVehicle(userId, addOrUpdateManualFamilyVehicleDTO);
=======
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<FamilyVehicle> addNewManualFamilyVehicle(@RequestBody AddNewManualFamilyVehicleDTO data) {

		FamilyVehicle familyVehicle = this.addNewManualFamilyVehicleOrchestrator.addNewManualFamilyVehicle(data.getUserId(), data.getVehicleId(),
				data.getMake(), data.getModel(), data.getYear(), data.getMileage(), data.getName());
>>>>>>> 8862bd4... merge all
		return new ResponseEntity<FamilyVehicle>(familyVehicle, null,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/manualvehicle/user/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> updateManualFamilyVehicle(@PathVariable("userId") final long userId,
<<<<<<< HEAD
			@RequestBody AddOrUpdateManualFamilyVehicleDTO addOrUpdateManualFamilyVehicleDTO) {

		this.addNewManualFamilyVehicleOrchestrator.updateManualFamilyVehicle(userId, addOrUpdateManualFamilyVehicleDTO);
=======
			@RequestBody VehicleGenericDescriptionDTO data) {

		this.addNewManualFamilyVehicleOrchestrator.updateManualFamilyVehicle(userId, data.getVehicleId(),
				data.getMake(), data.getModel(), data.getYear(), data.getMileage(), data.getName());
>>>>>>> 8862bd4... merge all
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}
	

	@RequestMapping(value = "/{vehicleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FamilyVehicle> getAdditionalVehicleDetails(
			@PathVariable("vehicleId") Long vehicleId) {
		FamilyVehicle familyVehicle = this.familyVehicleService
				.getItem(vehicleId);
		return new ResponseEntity<FamilyVehicle>(familyVehicle, null,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicles/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Set<VehicleConfirmationDTO>> getVehiclesToConfirm(
			@PathVariable("userId") long userId) throws NCDBApiException {
		Set<VehicleConfirmationDTO> result = this.vehicleConfirmationOrchestrator.getLocalAndNCDBVehiclesMixed(userId);
		return new ResponseEntity<Set<VehicleConfirmationDTO>>(result, null,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicles/confirm/user/{userId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> updateVehicleConfirmation(
			@PathVariable("userId") Long userId, @RequestBody List<VehicleConfirmationDTO> vehicleConfirmationDTOs)
			throws IOException, UserNotFoundException {
		this.vehicleConfirmationOrchestrator.confirmVehicles(userId, vehicleConfirmationDTOs);
		return new ResponseEntity<Object>(null, null, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicles/confirmed/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<StoreInfoAndFamilyVehiclesDTO> getFamilyVehiclesByUserId(
			@PathVariable("userId") final long userId) {
		isTrue(userId > 0, "The userId cannot be lower than 0");
		StoreInfoAndFamilyVehiclesDTO result = this.dashboardOrchestrator
				.getStoreAndConfirmedFamilyVehicleDetails(userId);
		return new ResponseEntity<StoreInfoAndFamilyVehiclesDTO>(result, null,
				HttpStatus.OK);

	}

    @RequestMapping("/trends/make/{make}/last")
    @ResponseBody
    public ResponseEntity<VehicleTrend> getTrend(@PathVariable("make") String make) throws VehicleTrendException {
    
    	VehicleTrend response = this.vehicleTrendService.getTrend(make);
    	return new ResponseEntity<VehicleTrend>(response, null, HttpStatus.OK);
    }       

    @RequestMapping("/vin/{vinNumber}")
    @ResponseBody
    public ResponseEntity<List<VehicleGenericDescriptionDTO>> getVehiclesByVINNumber(@PathVariable("vinNumber") final String vinNumber) {
    	List<VehicleGenericDescriptionDTO> response = this.ncdbService.getVehicleByVINNumber(vinNumber);
    	return new ResponseEntity<List<VehicleGenericDescriptionDTO>>(response, null, HttpStatus.OK);
    }
    
    @RequestMapping("/licenseplate/{licensePlate}")
    @ResponseBody
    public ResponseEntity<List<VehicleGenericDescriptionDTO>> getVehiclesByLicensePlate(@PathVariable("licensePlate") final String licensePlate) {
    	List<VehicleGenericDescriptionDTO> response = this.ncdbService.getVehicleByLicensePlate(licensePlate);
    	return new ResponseEntity<List<VehicleGenericDescriptionDTO>>(response, null, HttpStatus.OK);
    }
}
