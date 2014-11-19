package com.searshc.mygarage.services.ncdb;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scala.collection.mutable.StringBuilder;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

@Service
public class NcdbServiceImpl implements NcdbService {

	private static final Log log = LogFactory.getLog(NcdbServiceImpl.class);
	
    private NCDBApi ncdbApi;

    @Autowired
    public NcdbServiceImpl(NCDBApi ncdbApi) {
        this.ncdbApi = ncdbApi;
    }

    @Override
    public List<FamilyVehicle> listVehicles(Long familyId) throws NCDBApiException {
        return this.ncdbApi.getVehicles(familyId);
    }

    @Override
    public List<Order> getTransactions(Long familyId, Long tangibleId) throws NCDBApiException {
        return this.ncdbApi.getCarTransactionHistory(familyId, tangibleId);
    }
    
    @Override
    public int getHighestMileage(final Long familyId, final Long tangibleId) throws NCDBApiException {
    	List<Order> orders;

    	orders = this.getTransactions(familyId, tangibleId);

    	int highestMileage = -1;
    	if(orders != null && orders.size() > 0) {
    		for(Order order : orders) {
        		if(order.getOdometerAmount().intValue() > highestMileage) {
        			highestMileage = order.getOdometerAmount().intValue();
        		}
        	}
    	}
    	
    	return highestMileage;
    }
    
    public int getLastUsedStoreId(final Long familyId, final Long tangibleId) throws NCDBApiException {
    	List<Order> orders = this.getTransactions(familyId, tangibleId);
    	if(orders == null || orders.size() == 0) {
    		String msg = new StringBuilder()
    			.append("Could not determine the last store. There are no transactions available for family: ")
    			.append(familyId)
    			.append(" Tangible: ")
    			.append(tangibleId).toString();
    		log.warn(msg);
    		return -1;
    	}
    	Order lastOrder = null;
    	for(Order order : orders) {
    		if(lastOrder == null || order.getTransactionDateTime().after(lastOrder.getTransactionDateTime())) {
    			lastOrder = order;
    		}
    	}
    	return lastOrder.getStoreNumber();
    }

}
