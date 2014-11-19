package com.searshc.mygarage.services.record;

import java.util.List;

import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.repositories.RecordRepository;

@Service
public class RecordService extends GenericService<Record, Long, RecordRepository>{

	public List<Record> getRecordsByFamilyVehicleId(final Long familyVehicleId) {
		return  repository.getRecordsByFamilyVehicleId(familyVehicleId);
	}
	
	public void deleteRecord(final Long recordId) {
		repository.delete(recordId);
	}
	
	public int getHighestMileageByFamilyVehicleId(final Long familyVehicleId) {
		int mileage = -1;
		try {
			mileage = repository.getHighestMileageByFamilyVehicleId(familyVehicleId);
		} catch (NullPointerException e) {
			mileage = -1;
		}
		return mileage;
	}

}
