package com.searshc.mygarage.services;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.Vehicle;
import java.util.List;

public interface NcdbService {

    List<Vehicle> listVehicles(Integer ncdbId);

    List<Order> getTransactions(Integer ncdbId, Integer tangibleId);

}
