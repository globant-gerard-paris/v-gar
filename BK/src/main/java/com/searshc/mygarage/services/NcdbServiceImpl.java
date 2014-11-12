package com.searshc.mygarage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.exceptions.NCDBApiException;

@Service
public class NcdbServiceImpl implements NcdbService {

    private NCDBApi ncdbApi;

    @Autowired
    public NcdbServiceImpl(NCDBApi ncdbApi) {
        this.ncdbApi = ncdbApi;
    }

    @Override
    public List<Vehicle> listVehicles(Integer ncdbId) throws NCDBApiException {
        return this.ncdbApi.getVehicles(ncdbId);
    }

    @Override
    public List<Order> getTransactions(Integer ncdbId, Integer tangibleId) {
        return this.ncdbApi.getCarTransactionHistory(ncdbId, tangibleId);
    }

}
