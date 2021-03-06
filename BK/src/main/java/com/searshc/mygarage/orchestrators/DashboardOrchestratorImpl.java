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
public class DashboardOrchestratorImpl extends BaseOrchestrator implements DashboardOrchestrator {

	@Override
	public StoreInfoAndFamilyVehiclesDTO getStoreAndConfirmedFamilyVehicleDetails(long userId) {
		User user = this.userService.findByUserId(userId);

		Set<FamilyVehicle> confirmedFamilyVehicles = new HashSet<FamilyVehicle>();
		confirmedFamilyVehicles.addAll(this.familyVehicleService.getConfirmedFamilyVehiclesByUserId(userId));

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
		if(newestVehicle == null) {
			return null;
		}
		int storeId = this.ncdbService.getLastUsedStoreId(user.getFamilyId(), newestVehicle.getTangibleId());
		if(storeId <= 0 ) {
			return null;
		}
		return this.storeService.findBySacStore(String.valueOf(storeId));
	}
	

	
}
