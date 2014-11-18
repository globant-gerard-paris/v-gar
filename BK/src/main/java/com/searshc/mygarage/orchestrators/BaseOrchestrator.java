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

	
//	/**
//	 * @param userService
//	 * @param confirmedVehicleService
//	 * @param familyVehicleService
//	 * @param vehicleService
//	 * @param recordService
//	 * @param storeService
//	 * @param ncdbLocalService
//	 * @param ncdbService
//	 * @param vehicleRecallsService
//	 */
//	@Inject
//	public BaseOrchestrator(final UserService userService,
//			final ConfirmedVehicleService confirmedVehicleService,
//			final FamilyVehicleService familyVehicleService,
//			final VehicleService vehicleService, final RecordService recordService,
//			final StoreService storeService, final NCDBLocalService ncdbLocalService,
//			final NcdbService ncdbService, final VehicleRecallsService vehicleRecallsService) {
//		super();
//		this.userService = notNull(userService, "The User Service cannot be null");
//		this.confirmedVehicleService = notNull(confirmedVehicleService, "The ConfirmedVehicle Service cannot be null");
//		this.familyVehicleService = notNull(familyVehicleService, "The FamilyVehicle Service cannot be null");
//		this.vehicleService = notNull(vehicleService, "The Vehicle Service cannot be null");
//		this.recordService = notNull(recordService, "The Record Service cannot be null");
//		this.storeService = notNull(storeService, "The Store Service cannot be null");
//		this.ncdbLocalService = notNull(ncdbLocalService, "The NCDBLocal Service cannot be null");
//		this.ncdbService = notNull(ncdbService, "The NCDB Service cannot be null");
//		this.vehicleRecallsService = notNull(vehicleRecallsService, "The VehicleRecalls Service cannot be null");
//	}
	
	
	
	
}
