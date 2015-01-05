package com.searshc.mygarage.apis.ncdb;

import com.searshc.mygarage.apis.ncdb.response.order.OrderHistoryResponse;

import java.util.List;

import com.searshc.mygarage.dtos.VehicleGenericDescriptionDTO;
import com.searshc.mygarage.entities.FamilyVehicle;

public interface NCDBApi {

    List<FamilyVehicle> getVehicles(Long familyId);

    OrderHistoryResponse getVehicleHistory(Long familyId, Long tangibleId);

    List<VehicleGenericDescriptionDTO> getVehicleByVINNumber(final String vinNumber);

    List<VehicleGenericDescriptionDTO> getVehicleByLicensePlate(final String licensePlate);

}
