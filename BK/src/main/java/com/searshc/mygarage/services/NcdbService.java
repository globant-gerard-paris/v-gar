package com.searshc.mygarage.services;

import java.util.List;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NcdbService {

    List<Vehicle> listVehicles(final Long familyId) throws NCDBApiException;

    List<Order> getTransactions(final Long familyId, Long tangibleId) throws NCDBApiException;

}
