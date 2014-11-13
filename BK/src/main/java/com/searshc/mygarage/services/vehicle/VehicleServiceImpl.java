package com.searshc.mygarage.services.vehicle;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.repositories.VehicleRepository;

@Service
@Transactional
public class VehicleServiceImpl extends GenericService<Vehicle, Integer, VehicleRepository>{

	public Vehicle createAndSaveNewVehicle(final Long familyId, final Long tangibleId,
			final String make, final String model,final int year, final String color, final int mileage) {
		Vehicle vehicle = new Vehicle(familyId, tangibleId, make, model, year, color, mileage);
		return super.save(vehicle);
	}
	
	public List<Vehicle> getVehiclesByFamilyId(final Long familyId) {
		return repository.getVehiclesByFamilyId(familyId);
	}
	
	public List<Vehicle> getVehiclesByUserId(final Long userId) {
		return repository.getVehiclesByUserId(userId);
	} 
	
	public Set<Vehicle> combineNCDBAndLocalVehicles(final List<Vehicle> ncdbVehicles, final List<Vehicle> localVehicles) {
		Map<Long, Vehicle> ncdbLocallyStoreVehiclesMap = new HashMap<Long, Vehicle>();
		Long tangibleId;
		for(Vehicle vehicle : localVehicles) { 
			tangibleId = vehicle.getTangibleId();
			if (tangibleId != null && tangibleId >= 0) {
				ncdbLocallyStoreVehiclesMap.put(tangibleId, vehicle);
			}
		}
		
		Vehicle ncdbVehicleLocallyStore;
		for(Vehicle vehicle : ncdbVehicles) {
			ncdbVehicleLocallyStore = ncdbLocallyStoreVehiclesMap.get(vehicle.getTangibleId());
			if(ncdbVehicleLocallyStore != null) {
				vehicle.setId(ncdbVehicleLocallyStore.getId());
			}
		}
		
		Set<Vehicle> result = new HashSet<Vehicle>(localVehicles);
		result.addAll(ncdbVehicles);
		return result;
	}
	

}
