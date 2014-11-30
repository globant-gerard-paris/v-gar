package com.searshc.mygarage.orchestrators;

import com.searshc.mygarage.dtos.record.RecordDto;

public interface RecordOrchestrator {
	
	public void addRecord(final RecordDto recordDto, final Long familyVehicleId);
}
