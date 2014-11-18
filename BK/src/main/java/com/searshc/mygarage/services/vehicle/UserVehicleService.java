package com.searshc.mygarage.services.vehicle;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.repositories.UserVehicleRepository;

@Service
@Transactional
public class UserVehicleService extends GenericService<UserVehicle, Long, UserVehicleRepository>{

	public UserVehicle createAndSaveNewVehicle(final Long familyId, final Long tangibleId,
			final String make, final String model,final int year, final String color, final int mileage) {
		Vehicle  vehicle = new Vehicle(year, make, model, null, null); 
		UserVehicle userVehicle = new UserVehicle(vehicle, familyId, tangibleId, null, mileage, null);
		return super.save(userVehicle);
	}
	
	public List<UserVehicle> getVehiclesByFamilyId(final Long familyId) {
		return repository.getUserVehiclesByFamilyId(familyId);
	}
	
	public List<UserVehicle> getVehiclesByUserId(final Long userId) {
		return repository.getUserVehiclesByUserId(userId);
	}
	
	public UserVehicle getUserVehicleByTangibleId(final Long tangibleId) {
		return repository.getUserVehicleByTangibleId(tangibleId);
	}
	
	public void saveRecord(Long familyId, Long tangibleId, Record record) throws Exception {
		
	}
	
	public Set<UserVehicle> combineNCDBAndLocalVehicles(final List<UserVehicle> ncdbVehicles, final List<UserVehicle> localVehicles) {
		Map<Long, UserVehicle> ncdbLocallyStoreVehiclesMap = new HashMap<Long, UserVehicle>();
		Long tangibleId;
		for(UserVehicle userVehicle : localVehicles) { 
			tangibleId = userVehicle.getTangibleId();
			if (tangibleId != null && tangibleId >= 0) {
				ncdbLocallyStoreVehiclesMap.put(tangibleId, userVehicle);
			}
		}
		
		UserVehicle ncdbVehicleLocallyStore;
		for(UserVehicle userVehicle : ncdbVehicles) {
			ncdbVehicleLocallyStore = ncdbLocallyStoreVehiclesMap.get(userVehicle.getTangibleId());
			if(ncdbVehicleLocallyStore != null) {
				userVehicle.setId(ncdbVehicleLocallyStore.getId());
			}
		}
		
		Set<UserVehicle> result = new HashSet<UserVehicle>(localVehicles);
		result.addAll(ncdbVehicles);
		return result;
	}
	

}
