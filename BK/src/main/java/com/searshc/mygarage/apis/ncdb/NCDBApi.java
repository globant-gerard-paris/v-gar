package com.searshc.mygarage.apis.ncdb;

import com.searshc.mygarage.apis.ncdb.response.order.OrderHistoryResponse;
import java.util.List;

import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NCDBApi {

    List<FamilyVehicle> getVehicles(Long familyId) throws NCDBApiException;

    OrderHistoryResponse getVehicleHistory(Long familyId, Long tangibleId) throws NCDBApiException;

}
