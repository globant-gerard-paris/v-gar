package com.searshc.mygarage.services.nhtsa;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.apis.nhtsa.NHTSAApi;
import com.searshc.mygarage.apis.nhtsa.NHTSAUtils;
import com.searshc.mygarage.apis.nhtsa.response.NHTSARecalls;
import com.searshc.mygarage.entities.recalls.VehicleRecalls;
import com.searshc.mygarage.exceptions.NHTSARecallsException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class VehicleRecallsServiceImpl implements VehicleRecallsService {

    private NHTSAApi nhtsaApi;

    @Inject
    public VehicleRecallsServiceImpl(final NHTSAApi nhtsaApi) {
        this.nhtsaApi = Validate.notNull(nhtsaApi, "The NHTSA Api cannot be null");
    }

    @Override
    public List<VehicleRecalls> getRecalls(int year, String make, String model) {
        Validate.isTrue(!StringUtils.isEmpty(make), "The Make cannot be null or empty");
        Validate.isTrue(!StringUtils.isEmpty(model), "The Model cannot be null or empty");
    	NHTSARecalls nhtsaRecalls = null;
        try {
            nhtsaRecalls = this.nhtsaApi.getRecalls(year, make, model);
        } catch (NHTSARecallsException ex) {
            Logger.getLogger(VehicleRecallsServiceImpl.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return nhtsaRecalls == null ? new ArrayList<VehicleRecalls>() : NHTSAUtils.convert(nhtsaRecalls);
    }

    @Override
    public List<VehicleRecalls> getRecallsOrderedByDate(final int year, final String make,
            final String model, final boolean isAscending) {
        List<VehicleRecalls> recalls = this.getRecalls(year, make, model);
        Collections.sort(recalls, new Comparator<VehicleRecalls>() {

            @Override
            public int compare(VehicleRecalls o1, VehicleRecalls o2) {
                int multiplierFactor = isAscending == true ? 1 : -1;
                return o1.getReportReceivedDate().compareTo(o2.getReportReceivedDate()) * multiplierFactor;
            }
        });
        return recalls;
    }

    @Override
    public VehicleRecalls getLastRecall(final int year, final String make,
            final String model) {
        List<VehicleRecalls> recalls = this.getRecallsOrderedByDate(year, make, model, false);
        return recalls != null && recalls.size() > 0 ? recalls.get(0) : null;
    }

}
