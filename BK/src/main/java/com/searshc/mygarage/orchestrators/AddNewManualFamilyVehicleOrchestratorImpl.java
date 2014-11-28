package com.searshc.mygarage.orchestrators;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

import org.springframework.stereotype.Component;

import com.searshc.mygarage.entities.ConfirmedVehicle;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.exceptions.VehicleNotFoundException;

@Component
public class AddNewManualFamilyVehicleOrchestratorImpl extends BaseOrchestrator implements AddNewManualFamilyVehicleOrchestrator {


	@Override
	public FamilyVehicle addNewManualFamilyVehicle(final long userId, final long vehicleId, final String make,
			final String model, final int year, final double mileage, final String color, final String name) {
		isTrue(userId > 0, "The User Id must be greater than 0");
		notNull(make, "The Make cannot be null");
		notNull(model, "The Model cannot be null");
		isTrue(year > 0, "The Year is invalid");
		isTrue(mileage >= 0, "The Mileage cannot be lower than 0");
		
		User user = this.userService.findByUserId(userId);
		Vehicle vehicle = null;
		if(vehicleId > 0) {
			//If vehicleId is valid, retrieve an object based on that
			vehicle = this.vehicleService.getItem(vehicleId);
		} else {
			//if the vehicleId is invalid, find based on make-model-year
			vehicle = this.vehicleService.getVehicleByMakeModelAndYear(make, model, year);
			
			if(vehicle == null){
				//if there is no vehicle, create a new one
				vehicle = new Vehicle(year, make, model, null, null);
				vehicle = this.vehicleService.save(vehicle);
			}
		}
		
		
		FamilyVehicle familyVehicle = new FamilyVehicle(vehicle, user.getFamilyId(), null, color, mileage, name);
		familyVehicle = this.familyVehicleService.save(familyVehicle);
		
		ConfirmedVehicle confirmedVehicle = new ConfirmedVehicle(user, familyVehicle);
		this.confirmedVehicleService.save(confirmedVehicle);
		return familyVehicle;
	}
}
