package com.searshc.mygarage.apis.ncdb;

import com.searshc.mygarage.apis.ncdb.response.order.OrderHistoryResponse;
import java.util.List;

import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NCDBApi {

    List<UserVehicle> getVehicles(Long familyId) throws NCDBApiException;

    OrderHistoryResponse getVehiculeHistory(Long familyId, Long tangibleId) throws NCDBApiException;

}
