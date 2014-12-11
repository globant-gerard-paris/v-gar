package com.searshc.mygarage.orchestrators;

import com.searshc.mygarage.dtos.manualvehicle.AddOrUpdateManualFamilyVehicleDTO;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.exceptions.UserNotFoundException;
import com.searshc.mygarage.exceptions.VehicleNotFoundException;

public interface AddNewManualFamilyVehicleOrchestrator{

	/**
	 * Creates and save a new {@link FamilyVehicle}.
	 * There is no validation to prevent the user to insert two (manual) FamilyVehicle's with the same data.
	 * <br>It automatically confirms the vehicle for the user.
	 * @param userId the {@link User} id. Required.
	 * @param addOrUpdateManualFamilyVehicleDTO  an {@link AddOrUpdateManualFamilyVehicleDTO} object the vehicle data
	 * @throws UserNotFoundException if there is no user with the given id.
	 * @throws VehicleNotFoundException if there is no Vehicle with the given id or make-model-year set.
	 * @return an instance of {@link FamilyVehicle}
	 */
	FamilyVehicle addNewManualFamilyVehicle(final long userId, final AddOrUpdateManualFamilyVehicleDTO addOrUpdateManualFamilyVehicleDTO);
	
	void updateManualFamilyVehicle(final long userId, final AddOrUpdateManualFamilyVehicleDTO addOrUpdateManualFamilyVehicleDTO);
}