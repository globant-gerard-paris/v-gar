package com.searshc.mygarage.controllers.vehicle;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

import java.io.IOException;
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
import com.searshc.mygarage.entities.ConfirmedVehicle;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.recalls.VehicleRecalls;
import com.searshc.mygarage.entities.trends.VehicleTrend;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.exceptions.NHTSARecallsException;
import com.searshc.mygarage.exceptions.UserNotFoundException;
import com.searshc.mygarage.exceptions.VehicleTrendException;
import com.searshc.mygarage.orchestrators.DashboardOrchestrator;
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

	@Inject
	public VehicleController(final NcdbService ncdbService,
			final VehicleRecallsService nhtsaService,
			final FamilyVehicleService vehicleService,
			final RecordService recordService,
			final ConfirmedVehicleService confirmedVehicleService,
			final UserService userService, final ObjectMapper objectMapper,
			final DashboardOrchestrator dashboardOrchestrator,
			final VehicleTrendService vehicleTrendService) {
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

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FamilyVehicle> createAndSaveNewVehicle(
			@RequestParam("familyId") Long familyId,
			@RequestParam("tangibleId") Long tangibleId,
			@RequestParam("make") String make,
			@RequestParam("model") String model,
			@RequestParam("year") int year,
			@RequestParam("color") String color,
			@RequestParam("mileage") int mileage) {

		FamilyVehicle familyVehicle = this.familyVehicleService
				.createAndSaveNewVehicle(familyId, tangibleId, make, model,
						year, color, mileage);
		return new ResponseEntity<FamilyVehicle>(familyVehicle, null,
				HttpStatus.OK);
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
		User user = this.userService.getItem(userId);
		Set<VehicleConfirmationDTO> result = new HashSet<VehicleConfirmationDTO>();
		List<FamilyVehicle> localVehicles = this.familyVehicleService
				.getConfirmedFamilyVehiclesByUserId(user.getId());
		result.addAll(this.familyVehicleService.convert(localVehicles, true));
		if (user.getFamilyId() != null) {
			List<FamilyVehicle> ncdbVehicles = this.ncdbService
					.listVehicles(user.getFamilyId());
			ncdbVehicles.removeAll(localVehicles);
			result.addAll(this.familyVehicleService
					.convert(ncdbVehicles, false));
		}
		return new ResponseEntity<Set<VehicleConfirmationDTO>>(result, null,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicles/confirm/user/{userId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> updateVehicleConfirmation(
			@PathVariable("userId") Long userId, @RequestBody String jsonBody)
			throws IOException, UserNotFoundException {
		VehicleConfirmationDTO[] vehicleConfirmationDTOs = objectMapper
				.readValue(jsonBody, VehicleConfirmationDTO[].class);
		User user = this.userService.getItem(userId);
		if (user == null) {
			throw new UserNotFoundException("User not found with id: " + userId);
		}

		int recordsDeleted = this.confirmedVehicleService
				.deleteConfirmedVehiclesByUserId(userId);
		log.info(recordsDeleted
				+ " ConfirmedVehicles records deleted for userId " + userId);
		List<VehicleConfirmationDTO> vehicleDtoList = this.confirmedVehicleService
				.discardUnconfirmed(vehicleConfirmationDTOs);

		Set<ConfirmedVehicle> confirmedVehicles = this.familyVehicleService
				.convert(vehicleDtoList, user);
		Set<FamilyVehicle> newVehicles = this.confirmedVehicleService
				.extractNoPersistedVehicles(confirmedVehicles);
		if (newVehicles.size() > 0) {
			this.familyVehicleService.saveAndFlush(newVehicles);
		}
		confirmedVehicles = this.confirmedVehicleService
				.saveAndFlush(confirmedVehicles);
		log.info(confirmedVehicles.size() + " vehicles were confirmed");
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

}
