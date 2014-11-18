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
    public List<UserVehicle> listVehicles(Long ncdbId) throws NCDBApiException {
        return this.ncdbApi.getVehicles(ncdbId);
    }

    @Override
    public List<Order> getTransactions(Long ncdbId, Long tangibleId) {
        return this.ncdbApi.getCarTransactionHistory(ncdbId, tangibleId);
    }

}
