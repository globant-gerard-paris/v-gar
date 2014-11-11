package com.searshc.mygarage.controllers.vehicle;

import com.searshc.mygarage.entities.Record;

/**
 * @author Jero
 *
 */
public class RecordAssembly {

	public static Record toRecord(final RecordDto dto) {
		if (dto == null) {
			return null;
		}

		Record record = new Record();
		record.setComment(dto.getComment());
		record.setDate(dto.getDate());
		record.setService(dto.getService().getDesc());
		record.setSource(dto.getSource());
		record.setMileage(dto.getMileage());

		return record;
	}
}
