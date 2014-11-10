/**
 * 
 */
package com.searshc.mygarage.controllers.vehicle;

import com.searshc.mygarage.entities.Record;

/**
 * @author Jero
 *
 */
public class RecordAssembly {

	public static Record toRecord(RecordDto dto) {
		if (dto == null) {
			return null;
		}

		Record record = new Record();
		record.setComment(dto.getComment());
		// record.setDate(dto.getDate());
		record.setService(dto.getService());
		record.setSource(dto.getSource());

		return record;
	}
}
