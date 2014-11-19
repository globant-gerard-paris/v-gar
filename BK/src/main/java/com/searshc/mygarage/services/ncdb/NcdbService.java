package com.searshc.mygarage.services.ncdb;

import java.util.List;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

public interface NcdbService {

    List<FamilyVehicle> listVehicles(final Long familyId) throws NCDBApiException;
    
    int getHighestMileage(final Long familyId, final Long tangibleId) throws NCDBApiException;

    List<Order> getTransactions(Long familyId, Long tangibleId) throws NCDBApiException;
    
    int getLastUsedStoreId(final Long familyId, final Long tangibleId) throws NCDBApiException;

}
