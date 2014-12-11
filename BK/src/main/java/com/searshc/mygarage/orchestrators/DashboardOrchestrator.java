package com.searshc.mygarage.orchestrators;

import com.searshc.mygarage.dtos.StoreInfoAndFamilyVehiclesDTO;

public interface DashboardOrchestrator {

	StoreInfoAndFamilyVehiclesDTO getStoreAndConfirmedFamilyVehicleDetails(long userId);
}
