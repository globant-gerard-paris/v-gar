package com.searshc.mygarage.controllers.vehicle;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.entities.VehicleInformation;
import com.searshc.mygarage.services.vehicle.VehicleInformationService;

/**
 * 
 * The {@link VehicleInformationController} have the responsibility to manager the request about the
 * {@link VehicleInformation}s in the system.
 * 
 * @author Jero
 *
 */
@RestController
public class VehicleInformationController {

	@Inject
	private VehicleInformationService vehicleInformationService;

	@RequestMapping(value = "/family/{familyId}/tangible/{tangibleId}/records", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getRecords(
			@PathVariable("familyId") Long familyId,
			@PathVariable("tangibleId") Long tangibleId) throws Exception {
		List<Record> records = vehicleInformationService.findAllRecordsByFamilyIdAndTangibleId(
				familyId, tangibleId);
		if (CollectionUtils.isNotEmpty(records)) {
			return new ResponseEntity<Object>(records, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/family/{familyId}/tangible/{tangibleId}/record/{recordId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> deleteRecords(
			@PathVariable("familyId") Long familyId,
			@PathVariable("tangibleId") Long tangibleId,
			@PathVariable("recordId") Long recordId) throws Exception {
		
			vehicleInformationService.removeRecord(familyId, tangibleId, recordId);
			return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/family/{familyId}/tangible/{tangibleId}/record", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> addRecord(@RequestBody RecordDto recordDto,
			@PathVariable("familyId") Long familyId, @PathVariable("tangibleId") Long tangibleId)
			throws Exception {

		vehicleInformationService.addRecord(familyId, tangibleId, recordDto);

		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}