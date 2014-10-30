package com.searshc.mygarage.apis.ncdb;

import java.util.List;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.Vehicle;

public interface NCDBApi {

    List<Vehicle> getVehicles(Integer ncdbId);

    List<Order> getCarTransactionHistory(Integer ncdbId, Integer tangibleId);

}
