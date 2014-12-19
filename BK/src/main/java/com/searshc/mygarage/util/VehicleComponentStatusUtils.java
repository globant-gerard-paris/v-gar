package com.searshc.mygarage.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.searshc.mygarage.dtos.carprofile.VehicleStatusDTO;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.entities.record.ServiceRecordItem;

@Component
public class VehicleComponentStatusUtils {

	private Set<String> brakesSKUCodes = new HashSet<String>();
	private Set<String> tiresSKUCodes = new HashSet<String>();
	private Set<String> oilSKUCodes = new HashSet<String>();
	
	private Set<String> brakesProductFlags = new HashSet<String>();
	private Set<String> tiresProductFlags = new HashSet<String>();
	private Set<String> oilProductFlags = new HashSet<String>();
	
	public VehicleComponentStatusUtils() {
		brakesSKUCodes.add("02801300000"); //Complete Brake Evaluation
		
		tiresSKUCodes.add("02806422000");//Tire Pressure Monitoring System Evaluation
		tiresSKUCodes.add("02801315000"); //Suggested New Tires
		
		oilSKUCodes.add("02806672000"); //Adjust Low Oil Level
		
		brakesProductFlags.add("44"); //Brake Service
		brakesProductFlags.add("60"); //Brake Eval
		
		tiresProductFlags.add("41");//Tire Service
		
		oilProductFlags.add("49");//Oil Change Service
	}
	
	/*public VehicleStatusDTO processStatus(final List<ServiceRecord> mixedRecordsOrderedByDate) {
		ServiceRecord lastBrakesService = null;
		ServiceRecord lastTiresService = null;
		ServiceRecord lastOilService = null;
		List<ServiceRecordItem> items = null;
		for(ServiceRecord serviceRecord : mixedRecordsOrderedByDate) {
			for(ServiceRecordItem item : items) {
				if(serviceRecord.getType().equals(ServiceRecordType.LOCAL_SERVICE)) {
					//Find the category in suggested_service
					
					if(lastBrakesService == null && brakesSKUCodes.contains(item.getCode())) {
						lastBrakesService = serviceRecord;
						continue;
					}
					if(lastTiresService == null && tiresSKUCodes.contains(item.getCode())) {
						lastTiresService = serviceRecord;
						continue;
					}
					if(lastOilService == null && oilSKUCodes.contains(item.getCode())) {
						lastOilService = serviceRecord;
						continue;
					}
				} else {
					//Find the category in service_translation
					
					if(lastBrakesService == null && brakesProductFlags.contains(item.getCode())) {
						lastBrakesService = serviceRecord;
						continue;
					}
					if(lastTiresService == null && tiresProductFlags.contains(item.getCode())) {
						lastTiresService = serviceRecord;
						continue;
					}
					if(lastOilService == null && oilProductFlags.contains(item.getCode())) {
						lastOilService = serviceRecord;
						continue;
					}
				}
			}
			
			if(lastBrakesService != null && lastTiresService != null && lastOilService != null) {
				break;
			}
		}
		
	}
	
	public VehicleStatusDTO determineComponentStatus(final ServiceRecord lastBrakesServiceRecord,
			final ServiceRecord lastTiresServiceRecord,
			final ServiceRecord lastOilServiceRecord) {
		VehicleStatusDTO result = new VehicleStatusDTO();
		if(lastBrakesServiceRecord == null) {
			result.addComponentStatus(new Com);
		}
	}*/
}
