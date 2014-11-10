package com.searshc.mygarage.services.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.test.util.ReflectionTestUtils;

import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.entities.VehicleInformation;

/**
 * This service is mock because is still TBD and not implemented the way that vehicle is added to
 * the application.
 * 
 * @author Jero
 *
 */
@Component
public class VehicleServiceMock implements VehicleService {

	Map<Long, VehicleInformation> vehicleInformationRepositoryMock;
	private VehicleInformation mock;
	private Long testId = 786L;
	private Long id = 0L;

	public VehicleServiceMock() {
		vehicleInformationRepositoryMock = new ConcurrentHashMap<Long, VehicleInformation>();
		mock = new VehicleInformation();
		mock.setFamilyId(testId);
		mock.setTangibleId(testId);
		mock.setRecords(new ArrayList<Record>());
		vehicleInformationRepositoryMock.put(testId, mock);
	}

	@Override
	public void saveRecord(Long familyId, Long tangibleId, Record record) throws Exception {
		VehicleInformation vehicleInformation = vehicleInformationRepositoryMock.get(testId);
		List<Record> records = vehicleInformation.getRecords();
		ReflectionTestUtils.setField(record, "id", ++id);
		records.add(record);
	}

	@Override
	public void removeRecord(Long familyId, Long tangibleId, Long recordID) throws Exception {
		VehicleInformation vehicleInformation = vehicleInformationRepositoryMock.get(testId);
		List<Record> records = vehicleInformation.getRecords();
		List<Record> removedRecords = new ArrayList<Record>();
		for (Record record : records) {
			if (record.getId() != recordID) {
				removedRecords.add(record);
			}
		}
		vehicleInformation.setRecords(removedRecords);
	}

	@Override
	public VehicleInformation findByFamilyIdAndTangibleId(final Long familyId, final Long tangibleId)
			throws Exception {
		return mock;
	}
}
