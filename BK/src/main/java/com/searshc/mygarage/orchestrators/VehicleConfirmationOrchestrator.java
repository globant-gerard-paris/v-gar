package com.searshc.mygarage.orchestrators;

import java.util.List;
import java.util.Set;

import com.searshc.mygarage.dtos.VehicleConfirmationDTO;
import com.searshc.mygarage.entities.User;

public interface VehicleConfirmationOrchestrator {

	/**
	 * Gets the Vehicles classified into <strong>added Manually, linked from NCDB and not linked from NCDB</strong>
	 * @param userId the {@link User} id
	 * @return a set of {@link VehicleConfirmationDTO}.
	 */
	Set<VehicleConfirmationDTO> getLocalAndNCDBVehiclesMixed(final Long userId);
	
	void confirmVehicles(final Long userId, final List<VehicleConfirmationDTO> vehicleConfirmationDTOs);
}
