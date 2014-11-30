package com.searshc.mygarage.dtos.record;

import org.apache.commons.lang3.Validate;

import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.record.Record;
import com.searshc.mygarage.entities.record.SuggestedService;

/**
 * @author Jero
 *
 */
public class RecordAssembly {

    public static Record toRecord(final RecordDto dto, final FamilyVehicle familyVehicle, final SuggestedService suggestedService) {
        Validate.notNull(dto, "The dto object cannot be null");

        Record record = new Record();
        record.setComment(dto.getComment());
        record.setDate(dto.getDate());
        record.setSuggestedService(suggestedService);
        record.setSource(dto.getSource());
        record.setMileage(Integer.valueOf(dto.getMileage()));
        record.setFamilyVehicle(familyVehicle);

        return record;
    }
}
