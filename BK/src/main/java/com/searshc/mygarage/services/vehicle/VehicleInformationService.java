/**
 * 
 */
package com.searshc.mygarage.services.vehicle;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.controllers.vehicle.RecordAssembly;
import com.searshc.mygarage.controllers.vehicle.RecordDto;
import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.entities.VehicleInformation;
import com.searshc.mygarage.repositories.VehicleInformationRepository;

/**
 * The {@link VehicleInformationService} have the responsibility to update, delete and retrieve the
 * information regarding to vehicle from one {@code userId}.
 * 
 * @author Jero
 */
@Service
public class VehicleInformationService {

	@Inject
	private VehicleService vehicleService;

	public List<Record> findAllRecordsByFamilyIdAndTangibleId(final Long familyId,
			final Long tangibleId) throws Exception {
		Validate.notNull(familyId, "The familyId can't be null");
		Validate.notNull(tangibleId, "The tangibleId can't be null");

		VehicleInformation vehicle = vehicleService.findByFamilyIdAndTangibleId(familyId,
				tangibleId);
		Validate.notNull(vehicle, "Not found the vehicle information with family: " + familyId
				+ " and tangibleId: " + tangibleId + ".");

		return vehicle.getRecords();
	}

	public void addRecord(final Long familyId, final Long tangibleId, final RecordDto recordDto)
			throws Exception {
		Validate.notNull(familyId, "The familyId can't be null");
		Validate.notNull(tangibleId, "The tangibleId can't be null");
		validateRecord(recordDto);

		Record record = RecordAssembly.toRecord(recordDto);

		try {
			vehicleService.saveRecord(familyId, tangibleId, record);
		} catch (Exception e) {
			throw new Exception("An error occurred while try to save the new record : "
					+ recordDto.toString(), e);
		}
	}

	public void removeRecord(final Long familyId, final Long tangibleId, final Long recordId) throws Exception{
		Validate.notNull(familyId, "The familyId can't be null");
		Validate.notNull(tangibleId, "The tangibleId can't be null");
		Validate.notNull(recordId, "The recordId can't be null");
		
		vehicleService.removeRecord(familyId, tangibleId, recordId);
	}
	
	/**
	 * TODO: this would be good to implement with Spring <a href=
	 * "http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html"
	 * >Validation</a> instead of validation here.
	 * 
	 * @param dto
	 */
	private void validateRecord(RecordDto dto) {
		Validate.notNull(dto, "The record can't be null");
		Validate.notNull(dto.getDate(), "The record date can't be null");
		Validate.notNull(dto.getComment(), "The record comment can't be null");
		Validate.notNull(dto.getService(), "The record service can't be null");
		Validate.notNull(dto.getSource(), "The record source can't be null");
	}
}
