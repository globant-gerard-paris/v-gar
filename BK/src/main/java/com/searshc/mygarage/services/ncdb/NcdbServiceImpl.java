package com.searshc.mygarage.services.ncdb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.UserVehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

@Service
public class NcdbServiceImpl implements NcdbService {

    private NCDBApi ncdbApi;

    @Autowired
    public NcdbServiceImpl(NCDBApi ncdbApi) {
        this.ncdbApi = ncdbApi;
    }

    @Override
    public List<UserVehicle> listVehicles(Long familyId) throws NCDBApiException {
        return this.ncdbApi.getVehicles(familyId);
    }

    @Override
    public List<Order> getTransactions(Long familyId, Long tangibleId) throws NCDBApiException {
        return this.ncdbApi.getCarTransactionHistory(familyId, tangibleId);
    }

}
