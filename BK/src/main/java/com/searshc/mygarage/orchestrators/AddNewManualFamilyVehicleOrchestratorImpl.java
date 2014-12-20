package com.searshc.mygarage.orchestrators;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.crsh.console.jline.internal.Log;
import org.springframework.stereotype.Component;

import scala.collection.mutable.StringBuilder;

import com.searshc.mygarage.dtos.manualvehicle.AddOrUpdateManualFamilyVehicleDTO;
import com.searshc.mygarage.entities.ConfirmedVehicle;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.exceptions.CannotModifyLinkedVehicleException;
import com.searshc.mygarage.exceptions.FamilyVehicleNotConfirmedByUserException;

@Component
public class AddNewManualFamilyVehicleOrchestratorImpl extends BaseOrchestrator implements AddNewManualFamilyVehicleOrchestrator {

    @Override
    public FamilyVehicle addNewManualFamilyVehicle(final long userId, final AddOrUpdateManualFamilyVehicleDTO addOrUpdateManualFamilyVehicleDTO) {
        isTrue(userId > 0, "The User Id must be greater than 0");
        Long vehicleId = addOrUpdateManualFamilyVehicleDTO.getVehicleId();
        String make = addOrUpdateManualFamilyVehicleDTO.getMake();
        String model = addOrUpdateManualFamilyVehicleDTO.getModel();
        int year = addOrUpdateManualFamilyVehicleDTO.getYear();
        int mileage = addOrUpdateManualFamilyVehicleDTO.getMileage();
        String name = addOrUpdateManualFamilyVehicleDTO.getName();
        notNull(make, "The Make cannot be null");
        notNull(model, "The Model cannot be null");
        isTrue(year > 0, "The Year is invalid");
        isTrue(mileage >= 0, "The Mileage cannot be lower than 0");

        User user = this.userService.findByUserId(userId);
        Vehicle vehicle = null;
        if (vehicleId != null) {
            //If vehicleId is valid, retrieve an object based on that
            vehicle = this.vehicleService.getItem(vehicleId);
        } else {
            //if the vehicleId is invalid, find based on make-model-year
            vehicle = this.vehicleService.getVehicleByMakeModelAndYear(make, model, year);

            if (vehicle == null) {
                //if there is no vehicle, create a new one
                vehicle = new Vehicle(year, make, model, null, null);
                vehicle = this.vehicleService.save(vehicle);
            }
        }

        FamilyVehicle familyVehicle = new FamilyVehicle(vehicle, user.getFamilyId(), null, mileage, name);
        familyVehicle = this.familyVehicleService.save(familyVehicle);

        ConfirmedVehicle confirmedVehicle = new ConfirmedVehicle(user, familyVehicle);
        this.confirmedVehicleService.save(confirmedVehicle);
        return familyVehicle;
    }

    @Override
    public void updateManualFamilyVehicle(final long userId, final AddOrUpdateManualFamilyVehicleDTO addOrUpdateManualFamilyVehicleDTO) {
        Long familyVehicleId = addOrUpdateManualFamilyVehicleDTO.getFamilyVehicleId();
        String make = addOrUpdateManualFamilyVehicleDTO.getMake();
        String model = addOrUpdateManualFamilyVehicleDTO.getModel();
        int year = addOrUpdateManualFamilyVehicleDTO.getYear();
        int mileage = addOrUpdateManualFamilyVehicleDTO.getMileage();
        String name = addOrUpdateManualFamilyVehicleDTO.getName();

        Validate.notNull(familyVehicleId, "The FamilyVehicleId cannot be null");
		User user = this.userService.findByUserId(userId);
        FamilyVehicle familyVehicle = this.familyVehicleService.getItem(familyVehicleId);
        validateConfirmedVehicle(user,familyVehicle);

        Vehicle vehicleDetails = familyVehicle.getVehicle();
        if (!vehicleDetails.getModel().equalsIgnoreCase(model)
                || !vehicleDetails.getMake().equalsIgnoreCase(make)
                || vehicleDetails.getYear() != year) {
            //The user has change the model, make, year or all of them
            if (familyVehicle.getTangibleId() != null) {
                String msg = new StringBuilder("Cannot Modify FamilyVehicle with id: ")
                        .append(familyVehicleId).append(" because is not a manual vehicle").toString();
                Log.error(msg);
                throw new CannotModifyLinkedVehicleException(msg);
            }

            Vehicle vehicle = this.vehicleService.getVehicleByMakeModelAndYearOrCreateNewVehicle(make, model, year);
            familyVehicle.setVehicle(vehicle);
        }

        if (familyVehicle.getMileage() != mileage) {
            familyVehicle.setMileage(mileage);
            familyVehicle.setLastMileageUpdate(Calendar.getInstance().getTime());
        }

        familyVehicle.setName(name);
        this.familyVehicleService.saveAndFlush(familyVehicle);
    }
    
    @Override
    public List<String> getDistinctYears() {
    	return this.vehicleService.getDistinctYears();
    }    
    
    @Override
    public List<String> getDistinctMakesByYear(final int year){
    	return this.vehicleService.getDistinctMakesByYear(year);
    }

    @Override
    public List<String> getDistinctModelsByYearMake(final int year, final String make){
    	return this.vehicleService.getDistinctModelsByYearMake(year, make);
    }


    @Override
    public void updateFamilyVehicleName(final long userId, final AddOrUpdateManualFamilyVehicleDTO addOrUpdateManualFamilyVehicleDTO) {
        Long familyVehicleId = addOrUpdateManualFamilyVehicleDTO.getFamilyVehicleId();
        String name = addOrUpdateManualFamilyVehicleDTO.getName();
        Validate.notNull(userId, "The UserId cannot be null");
        Validate.notNull(familyVehicleId, "The FamilyVehicleId cannot be null");
        Validate.notNull(name, "The Name cannot be null");
		User user = this.userService.findByUserId(userId);
        FamilyVehicle familyVehicle = this.familyVehicleService.getItem(familyVehicleId);

        validateConfirmedVehicle(user,familyVehicle);

        familyVehicle.setName(name);
        this.familyVehicleService.saveAndFlush(familyVehicle);
    }

	private void validateConfirmedVehicle(final User user,
			FamilyVehicle familyVehicle) throws FamilyVehicleNotConfirmedByUserException{
        if (!this.confirmedVehicleService.isAConfirmedVehicleByTheUser(user, familyVehicle)) {
            String msg = new StringBuilder("The FamilyVehicle with id: ")
                    .append(familyVehicle.getId()).append(" is not confirmed by user with id: ").append(user.getId()).toString();
            Log.error(msg);
            throw new FamilyVehicleNotConfirmedByUserException(msg);
        }
	}

}
