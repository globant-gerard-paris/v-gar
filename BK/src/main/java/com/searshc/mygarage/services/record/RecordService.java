package com.searshc.mygarage.services.record;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.entities.record.Record;
import com.searshc.mygarage.entities.record.SuggestedService;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.repositories.RecordRepository;
import com.searshc.mygarage.repositories.SuggestedServiceRepository;
import com.searshc.mygarage.services.ncdb.NcdbService;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class RecordService extends GenericService<Record, Long, RecordRepository> {

    private NcdbService ncdbService;

    private SuggestedServiceRepository suggestedServiceRepository;

    @Inject
    public RecordService(final NcdbService ncdbService, SuggestedServiceRepository suggestedServiceRepository) {
        this.ncdbService = Validate.notNull(ncdbService, "The NCDB Service cannot be null");
        this.suggestedServiceRepository = Validate.notNull(suggestedServiceRepository, "The SuggestedService Repository cannot be null");
    }

    public List<Record> getRecordsByFamilyVehicleId(final Long familyVehicleId) {
        return repository.getRecordsByFamilyVehicleId(familyVehicleId);
    }

    public void deleteRecord(final Long recordId) {
        repository.delete(recordId);
    }

    public List<ServiceRecord> getNcdbServiceRecords(final Long familyIdNumber, final Long tangibleId)
            throws NCDBApiException {
        return this.ncdbService.getServiceRecords(familyIdNumber, tangibleId);
    }

    public List<ServiceRecord> getServiceRecords(final Long vehicleId) throws NCDBApiException {
        //TODO: query the local database
        //TODO: merge both results
        //TODO: sort results
        return null;
    }

    public RecommendedService getRecommendedServices(Long familyId, Long tangibleId)
            throws NCDBApiException {

        return this.ncdbService.getRecommendedServices(familyId, tangibleId);

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

    public List<SuggestedService> getSuggestedServices() {
        return this.suggestedServiceRepository.findAll();
    }
}
