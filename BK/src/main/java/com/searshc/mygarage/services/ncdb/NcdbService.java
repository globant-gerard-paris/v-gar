package com.searshc.mygarage.services.ncdb;

import com.searshc.mygarage.dtos.VehicleGenericDescriptionDTO;
import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.entities.record.ServiceRecord;

import java.util.List;

import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NcdbService {

    List<ServiceRecord> getServiceRecords(Long familyId, Long tangibleId);

    RecommendedService getRecommendedServices(Long familyId, Long tangibleId);

    List<FamilyVehicle> listVehicles(final Long familyId);

    int getHighestMileage(final Long familyId, final Long tangibleId) throws NCDBApiException;

    int getLastUsedStoreId(final Long familyId, final Long tangibleId) throws NCDBApiException;

    List<VehicleGenericDescriptionDTO> getVehicleByVINNumber(final String vinNumber) throws NCDBApiException;

    List<VehicleGenericDescriptionDTO> getVehicleByLicensePlate(final String licensePlate) throws NCDBApiException;

}
