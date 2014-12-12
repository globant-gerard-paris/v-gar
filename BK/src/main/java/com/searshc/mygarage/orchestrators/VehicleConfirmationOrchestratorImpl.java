package com.searshc.mygarage.orchestrators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.searshc.mygarage.dtos.VehicleConfirmationDTO;
import com.searshc.mygarage.dtos.VehicleConfirmationDTO.Status;
import com.searshc.mygarage.entities.ConfirmedVehicle;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.User;

@Component
public class VehicleConfirmationOrchestratorImpl extends BaseOrchestrator implements VehicleConfirmationOrchestrator {

	private static final Log log = LogFactory.getLog(VehicleConfirmationOrchestratorImpl.class);
	
	private static final String MANUAL_VEHICLES = "manualVehicles";
	
	private static final String LINKED_VEHICLES = "linkedVehicles";
	
	private static final String NOT_LINKED_VEHICLES = "notLinkedVehicles";
	
	@Override
	public Set<VehicleConfirmationDTO> getLocalAndNCDBVehiclesMixed(final Long userId) {
		Validate.notNull(userId, "The UserId cannot be null");
		Set<VehicleConfirmationDTO> result = new HashSet<VehicleConfirmationDTO>();
		User user = this.userService.getItem(userId);
		List<FamilyVehicle> localVehiclesConfirmed = this.familyVehicleService.getConfirmedFamilyVehiclesByUserId(userId);
		log.debug(localVehiclesConfirmed.size() + " vehicles return as confirmed, including manual and linked");
		if(user.getFamilyId() != null) {
			//mix manual with ncdb vehicles 
			List<FamilyVehicle> ncdbVehicles = this.ncdbService.listVehicles(user.getFamilyId());
			Map<String, List<FamilyVehicle>> classifiedVehicles = this.mixVehicles(localVehiclesConfirmed, ncdbVehicles);
			
			//Convert to DTO and add to result
			result.addAll(this.convert(classifiedVehicles.get(MANUAL_VEHICLES), Status.MANUAL));
			result.addAll(this.convert(classifiedVehicles.get(LINKED_VEHICLES), Status.LINKED));
			result.addAll(this.convert(classifiedVehicles.get(NOT_LINKED_VEHICLES), Status.NCDB));
		} else {
			//only manual added vehicles
			result.addAll(this.convert(localVehiclesConfirmed, Status.MANUAL));
		}
		return result;
	}
	
	@Override
	public void confirmVehicles(final Long userId, final List<VehicleConfirmationDTO> vehicleConfirmationDTOs) {
		Validate.notNull(userId, "The userId cannot be null");
		Validate.notNull(vehicleConfirmationDTOs, "The Vehicle Confirmation DTO's cannot be null");
		User user = this.userService.getItem(userId);
		int recordsDeleted = this.confirmedVehicleService.deleteConfirmedVehiclesByUserId(userId);
		log.info(recordsDeleted + " ConfirmedVehicles records deleted for userId " + userId);
		
		List<VehicleConfirmationDTO> vehicleDtoList = this.confirmedVehicleService
				.discardUnconfirmed(vehicleConfirmationDTOs);
		log.debug(vehicleDtoList.size() + " vehicles to be confirmed");

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
	}
	
	/**
	 * Classify and separates in 3 different lists the vehicles.
	 * <br>The classification is based on:
	 * <ol><strong>Manual Vehicles:</strong> those which are confirmed but don't have tangibleId</ol>
	 * <ol><strong>Linked Vehicles:</strong> those which are confirmed and have tangibleId</ol>
	 * <ol><strong>Not Linked Vehicles:</strong> those which are not confirmed</ol>
	 * @param confirmedLocalVehicles the {@link FamilyVehicles} that are confirmed by {@link ConfirmedVehicle}
	 * @param ncdbVehicles the {@link FamilyVehicle} retrieved from NCDB
	 * @return a Map with three entries with the following keys:
	 * <ol>{@link VehicleConfirmationOrchestratorImpl#MANUAL_VEHICLES}</ol>
	 * <ol>{@link VehicleConfirmationOrchestratorImpl#LINKED_VEHICLES}</ol>
	 * <ol>{@link VehicleConfirmationOrchestratorImpl#NOT_LINKED_VEHICLES}</ol>
	 */
	public Map<String, List<FamilyVehicle>> mixVehicles(final List<FamilyVehicle> confirmedLocalVehicles, final List<FamilyVehicle> ncdbVehicles){
		Map<String, List<FamilyVehicle>> result = new HashMap<String, List<FamilyVehicle>>();
		List<FamilyVehicle> manualVehicles = new ArrayList<FamilyVehicle>();
		List<FamilyVehicle> linkedVehicles = new ArrayList<FamilyVehicle>();
		List<FamilyVehicle> notLinkedVehicles = new ArrayList<FamilyVehicle>();
		result.put(MANUAL_VEHICLES, manualVehicles);
		result.put(LINKED_VEHICLES, linkedVehicles);
		result.put(NOT_LINKED_VEHICLES, notLinkedVehicles);
		
		if(CollectionUtils.isEmpty(confirmedLocalVehicles)) {
			notLinkedVehicles.addAll(ncdbVehicles);
			return result;
		}
		if(CollectionUtils.isEmpty(ncdbVehicles)) {
			manualVehicles.addAll(confirmedLocalVehicles);
			return result;
		}
		
		//Determine linked and not linked vehicles
		FamilyVehicle linkedVehicle;
		for(FamilyVehicle ncdbVehicle : ncdbVehicles) {
			linkedVehicle = null;
			for(FamilyVehicle localVehicle : confirmedLocalVehicles) {
				if(ncdbVehicle.getTangibleId().equals(localVehicle.getTangibleId())) {
					linkedVehicle = localVehicle;
					break;
				}
			}
			if(linkedVehicle != null) {
				linkedVehicles.add(linkedVehicle);
			} else {
				notLinkedVehicles.add(ncdbVehicle);
			}
			
		}
		
		//The manual vehicles are those confirmedVehicles that are not linked
		manualVehicles.addAll(confirmedLocalVehicles);
		manualVehicles.removeAll(linkedVehicles);
		log.debug(new StringBuilder("Vehicles classified as Manual: ").append(manualVehicles.size())
				.append(" - Vehicles classified as Linked: ").append(linkedVehicles.size())
				.append(" - Vehicles classified as Not Linked: ").append(notLinkedVehicles.size()).toString());
		return result;
	}

    public Set<VehicleConfirmationDTO> convert(final List<FamilyVehicle> familyVehicles, final Status status) {
        Set<VehicleConfirmationDTO> result = new HashSet<VehicleConfirmationDTO>();
        VehicleConfirmationDTO dto;
        for (FamilyVehicle familyVehicle : familyVehicles) {
            dto = new VehicleConfirmationDTO();
            dto.setVehicleId(familyVehicle.getId());
            dto.setFamilyId(familyVehicle.getFamilyId());
            dto.setTangibleId(familyVehicle.getTangibleId());
            dto.setMake(familyVehicle.getVehicle().getMake());
            dto.setModel(familyVehicle.getVehicle().getModel());
            dto.setYear(familyVehicle.getVehicle().getYear());
            dto.setEngine(familyVehicle.getVehicle().getEngine());
            dto.setConfirmed(status == Status.NCDB ? false : true);
            dto.setStatus(status);
            result.add(dto);
        }
        return result;
    }
}
