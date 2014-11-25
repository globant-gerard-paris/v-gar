package com.searshc.mygarage.services.ncdb;

import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.entities.record.ServiceRecord;
import java.util.List;

import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NcdbService {

    List<ServiceRecord> getServiceRecords(Long familyId, Long tangibleId)
            throws NCDBApiException;

    RecommendedService getRecommendedServices(Long familyId, Long tangibleId)
            throws NCDBApiException;

    List<FamilyVehicle> listVehicles(final Long familyId) throws NCDBApiException;

    int getHighestMileage(final Long familyId, final Long tangibleId) throws NCDBApiException;

    int getLastUsedStoreId(final Long familyId, final Long tangibleId) throws NCDBApiException;

}
