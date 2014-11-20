package com.searshc.mygarage.services.ncdb;

import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.entities.record.ServiceRecord;
import java.util.List;

import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NcdbService {

    List<UserVehicle> listVehicles(final Long familyId)
            throws NCDBApiException;

    List<ServiceRecord> getServiceRecords(Long familyId, Long tangibleId)
            throws NCDBApiException;

    RecommendedService getRecommendedServices(Long familyId, Long tangibleId)
            throws NCDBApiException;

}
