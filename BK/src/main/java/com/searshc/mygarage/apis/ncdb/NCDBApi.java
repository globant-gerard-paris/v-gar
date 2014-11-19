package com.searshc.mygarage.apis.ncdb;

import java.util.List;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NCDBApi {

    List<FamilyVehicle> getVehicles(Long familyId) throws NCDBApiException;

    List<Order> getCarTransactionHistory(Long familyId, Long tangibleId) throws NCDBApiException;

}
