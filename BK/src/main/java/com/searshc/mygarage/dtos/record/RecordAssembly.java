package com.searshc.mygarage.dtos.record;

import org.apache.commons.lang3.Validate;

import com.searshc.mygarage.entities.record.Record;

/**
 * @author Jero
 *
 */
public class RecordAssembly {

    public static Record toRecord(final RecordDto dto) {
        Validate.notNull(dto, "The dto object cannot be null");

        Record record = new Record();
        record.setComment(dto.getComment());
        record.setDate(dto.getDate());
        //TODO: Change DTO to work with SuggestedService
        //record.setService(dto.getService().getDesc());
        record.setSource(dto.getSource());
        record.setMileage(Integer.valueOf(dto.getMileage()));

        return record;
    }
}
