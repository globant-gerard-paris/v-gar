package com.searshc.mygarage.apis.ncdb;

import java.util.List;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NCDBApi {

	List<Vehicle> getVehicles(Integer familyIdNumber) throws NCDBApiException;

    List<Order> getCarTransactionHistory(Integer ncdbId, Integer tangibleId);

}
