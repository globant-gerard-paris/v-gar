package com.searshc.mygarage.services.ncdb;

import java.util.List;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NcdbService {

    List<UserVehicle> listVehicles(final Long familyId) throws NCDBApiException;

    List<Order> getTransactions(Long familyId, Long tangibleId) throws NCDBApiException;

}
