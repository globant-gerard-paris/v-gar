package com.searshc.mygarage.apis.ncdb;

import java.util.List;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NCDBApi {

    List<UserVehicle> getVehicles(Long familyId) throws NCDBApiException;

    List<Order> getCarTransactionHistory(Long familyId, Long tangibleId) throws NCDBApiException;

}
