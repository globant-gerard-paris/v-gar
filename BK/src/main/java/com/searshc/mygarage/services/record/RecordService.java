package com.searshc.mygarage.services.record;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.dtos.ServiceRecord;
import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.repositories.RecordRepository;
import com.searshc.mygarage.services.ncdb.NcdbService;
import org.springframework.stereotype.Service;

@Service
public class RecordService extends GenericService<Record, Long, RecordRepository> {

    private NcdbService ncdbService;

    @Inject
    public RecordService(final NcdbService ncdbService) {
        this.ncdbService = Validate.notNull(ncdbService, "The NCDB Service cannot be null");
    }

    public List<Record> getLocalRecordsByUserVehicleId(final Long userVehicleId) {
        return repository.getRecordsByUserVehicleId(userVehicleId);
    }

    public void deleteRecord(final Long recordId) {
        repository.delete(recordId);
    }

    public List<ServiceRecord> getNcdbServiceRecords(final Long familyIdNumber, final Long tangibleId) throws NCDBApiException {                
        return this.ncdbService.getServiceRecords(familyIdNumber, tangibleId);        
    }
    
    public List<ServiceRecord> getServiceRecords(final Long vehicleId) throws NCDBApiException {        
        //TODO: query the local database
        //TODO: merge both results
        //TODO: sort results
        return null;
    }
}
