package com.searshc.mygarage.services.record;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.record.LocalServiceRecord;
import com.searshc.mygarage.entities.record.RealServiceRecordItem;
import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.entities.record.Record;
import com.searshc.mygarage.entities.record.ServiceRecordItem;
import com.searshc.mygarage.entities.record.SuggestedService;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.exceptions.SuggestedServiceNotFoundException;
import com.searshc.mygarage.repositories.FamilyVehicleRepository;
import com.searshc.mygarage.repositories.RecordRepository;
import com.searshc.mygarage.repositories.SuggestedServiceRepository;
import com.searshc.mygarage.services.ncdb.NcdbService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class RecordService extends GenericService<Record, Long, RecordRepository> {

    private NcdbService ncdbService;

    private SuggestedServiceRepository suggestedServiceRepository;

    private FamilyVehicleRepository familyVehicleRepository;

    @Inject
    public RecordService(final NcdbService ncdbService,
            SuggestedServiceRepository suggestedServiceRepository,
            FamilyVehicleRepository familyVehicleRepository) {
        this.ncdbService = Validate.notNull(ncdbService, "The NCDB Service cannot be null");
        this.suggestedServiceRepository = Validate.notNull(suggestedServiceRepository, "The SuggestedService Repository cannot be null");
        this.familyVehicleRepository = Validate.notNull(familyVehicleRepository, "FamilyVehicleRepository cannot be null");
    }

    public void deleteRecord(final Long recordId) {
        repository.deleteRecord(recordId);
    }

    private LocalServiceRecord createLocalServiceRecord(Record record) {
        ServiceRecordItem sri = new RealServiceRecordItem();
        if (record.getSuggestedService() != null) {
            sri.setCode(record.getSuggestedService().getSku());
            sri.setDescription(record.getSuggestedService().getDescription());
        }
        return new LocalServiceRecord(record.getId(), record.getSource(),
                record.getMileage(), record.getDate(), sri, record.getComment());
    }

    public List<ServiceRecord> getServiceRecords(final Long familyVehicleId) throws NCDBApiException {
        List<ServiceRecord> result = new ArrayList<ServiceRecord>();
        FamilyVehicle fv = this.familyVehicleRepository.findOne(familyVehicleId);
        if (fv != null && fv.getFamilyId() != null && fv.getTangibleId() != null) {
            result = this.ncdbService.getServiceRecords(fv.getFamilyId(), fv.getTangibleId());
        }
        List<Record> records = repository.getRecordsByFamilyVehicleId(familyVehicleId);
        for (Record record : records) {
            result.add(this.createLocalServiceRecord(record));
        }
        Collections.sort(result, new Comparator<ServiceRecord>() {
            @Override
            public int compare(ServiceRecord o1, ServiceRecord o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        this.updateMileage(fv, result);

        return result;
    }

    private void updateMileage(FamilyVehicle fv, List<ServiceRecord> result) {
        if (!result.isEmpty()) {
            ServiceRecord sr = result.get(0);
            if (fv.getLastMileageUpdate() == null
                    || sr.getDate().after(fv.getLastMileageUpdate())) {
                fv.setMileage(sr.getMileage());
                fv.setLastMileageUpdate(sr.getDate());
                this.familyVehicleRepository.saveAndFlush(fv);
            }
        }
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

    public SuggestedService getSuggestedServiceById(final Long id) {
        SuggestedService suggestedService = this.suggestedServiceRepository.findOne(id);
        if (suggestedService == null) {
            throw new SuggestedServiceNotFoundException("SuggestedService not found with id: " + id);
        }
        return suggestedService;
    }
}
