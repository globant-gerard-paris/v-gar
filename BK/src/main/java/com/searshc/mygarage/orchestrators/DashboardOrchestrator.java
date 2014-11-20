package com.searshc.mygarage.orchestrators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.searshc.mygarage.dtos.StoreInfoAndFamilyVehiclesDTO;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.Store;
import com.searshc.mygarage.entities.User;

@Component
public class DashboardOrchestrator extends BaseOrchestrator{

	public StoreInfoAndFamilyVehiclesDTO getStoreAndConfirmedFamilyVehicleDetails(long userId) {
		User user = this.userService.findByUserId(userId);
		Set<FamilyVehicle> confirmedFamilyVehicles = this.getConfirmedFamilyVehicleWithUpdatedMileage(userId);
		//update the database information
		confirmedFamilyVehicles = this.familyVehicleService.save(confirmedFamilyVehicles);
		Store store = null;
		if(user.getStore() != null) {
			store = user.getStore();
		} else {
			store = this.findLastUsedStore(user, confirmedFamilyVehicles);
			if(store != null) {
				user.setStore(store);
				this.userService.save(user);
			}
		}
		return new StoreInfoAndFamilyVehiclesDTO(store, confirmedFamilyVehicles);
	}
	
	   
    public Set<FamilyVehicle> getConfirmedFamilyVehicleWithUpdatedMileage(final long userId) {
    	this.userService.getItem(userId);
    	
    	List<FamilyVehicle> familyVehicles = this.familyVehicleService.getConfirmedFamilyVehiclesByUserId(userId);
    	
    	int highestMileage, localMileage, NcdbMileage;
    	for(FamilyVehicle familyVehicle : familyVehicles) {
    		highestMileage = familyVehicle.getMileage();
    		localMileage = this.recordService.getHighestMileageByFamilyVehicleId(familyVehicle.getId());
    		highestMileage = familyVehicle.getMileage() > localMileage ? familyVehicle.getMileage() : localMileage;
    		if(familyVehicle.getTangibleId() != null) {
    			NcdbMileage = this.ncdbService.getHighestMileage(familyVehicle.getFamilyId(), familyVehicle.getTangibleId());
    			highestMileage = NcdbMileage > highestMileage ? NcdbMileage : highestMileage;
    		}
    		familyVehicle.setMileage(highestMileage);
    	}
    	return new HashSet<FamilyVehicle>(familyVehicles);
    	
    }
	
	/**
	 * Based on a list of {@link FamilyVehicle}, it looks for the newest linked vehicle (having familyId)
	 * and gets the store in which the last service was done.
	 * @param user the current user
	 * @param confirmedFamilyVehicles a list of FamilyVehicle confirmed by the user
	 * @return the {@link Store} if any, otherwise <code>null</code>
	 */
	public Store findLastUsedStore(final User user, final Set<FamilyVehicle> confirmedFamilyVehicles) {
		if(user.getFamilyId() == null || confirmedFamilyVehicles.size() == 0) {
			return null;
		}

		FamilyVehicle newestVehicle = null;
		for(FamilyVehicle familyVehicle : confirmedFamilyVehicles) {
			if(familyVehicle.getTangibleId() != null && (newestVehicle == null || familyVehicle.getVehicle().getYear() > newestVehicle.getVehicle().getYear())) {
				newestVehicle = familyVehicle;
			}
		}
		
		int storeId = this.ncdbService.getLastUsedStoreId(user.getFamilyId(), newestVehicle.getTangibleId());
		if(storeId <= 0 ) {
			return null;
		}
		return this.storeService.getItem(Long.valueOf(storeId));
	}
	

	
}
