package com.searshc.mygarage.orchestrators;

import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.exceptions.UserNotFoundException;
import com.searshc.mygarage.exceptions.VehicleNotFoundException;

public interface AddNewManualFamilyVehicleOrchestrator{

	/**
	 * Creates and save a new {@link FamilyVehicle}.
	 * There is no validation to prevent the user to insert two (manual) FamilyVehicle's with the same data.
	 * @param userId the {@link User} id. Required.
	 * @param vehicleId the {@link Vehicle} id. Required.
	 * @param make the {@link FamilyVehicle} make. Required.
	 * @param model the {@link FamilyVehicle} model. Required.
	 * @param year the {@link FamilyVehicle} year. Required.
	 * @param mileage the {@link FamilyVehicle} mileage. Required.
	 * @param color the {@link FamilyVehicle} color.
	 * @param name the {@link FamilyVehicle} name.
	 * @throws UserNotFoundException if there is no user with the given id.
	 * @throws VehicleNotFoundException if there is no Vehicle with the given id or make-model-year set.
	 * @return an instance of {@link FamilyVehicle}
	 */
	public FamilyVehicle addNewManualFamilyVehicle(final long userId, final long vehicleId, final String make,
			final String model, final int year, final double mileage, final String color, final String name);
}
