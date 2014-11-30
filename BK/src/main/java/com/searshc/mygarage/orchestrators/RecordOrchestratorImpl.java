package com.searshc.mygarage.orchestrators;

import org.springframework.stereotype.Component;

import com.searshc.mygarage.dtos.record.RecordAssembly;
import com.searshc.mygarage.dtos.record.RecordDto;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.record.Record;
import com.searshc.mygarage.entities.record.SuggestedService;

@Component
public class RecordOrchestratorImpl extends BaseOrchestrator implements RecordOrchestrator {

	public void addRecord(final RecordDto recordDto, final Long familyVehicleId) {
		FamilyVehicle familyVehicle = this.familyVehicleService.getItem(familyVehicleId);
		SuggestedService suggestedService = this.recordService.getSuggestedServiceById(Long.valueOf(recordDto.getService().getId()));
		Record record = RecordAssembly.toRecord(recordDto, familyVehicle, suggestedService);
		this.recordService.save(record);
	}
}
