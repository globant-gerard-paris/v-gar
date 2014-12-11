package com.searshc.mygarage.orchestrators;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.searshc.mygarage.services.ncdb.NCDBLocalService;
import com.searshc.mygarage.services.ncdb.NcdbService;
import com.searshc.mygarage.services.nhtsa.VehicleRecallsService;
import com.searshc.mygarage.services.record.RecordService;
import com.searshc.mygarage.services.store.StoreService;
import com.searshc.mygarage.services.user.UserService;
import com.searshc.mygarage.services.vehicle.ConfirmedVehicleService;
import com.searshc.mygarage.services.vehicle.FamilyVehicleService;
import com.searshc.mygarage.services.vehicle.VehicleService;

@Component
public abstract class BaseOrchestrator {

	@Inject
	protected UserService userService;
	@Inject
	protected ConfirmedVehicleService confirmedVehicleService;
	@Inject
	protected FamilyVehicleService familyVehicleService;
	@Inject
	protected VehicleService vehicleService;
	
	@Inject
	protected RecordService recordService;
	@Inject
	protected StoreService storeService;
	
	@Inject
	protected NCDBLocalService ncdbLocalService;
	@Inject
	protected NcdbService ncdbService;
	
	@Inject
	protected VehicleRecallsService vehicleRecallsService;
	
}
