package com.searshc.mygarage.services.trends;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.apis.trends.TrendReaderApi;
import com.searshc.mygarage.apis.trends.TrendReaderUtils;
import com.searshc.mygarage.apis.trends.response.TrendReader;
import com.searshc.mygarage.entities.trends.VehicleTrend;
import com.searshc.mygarage.exceptions.VehicleTrendException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class VehicleTrendServiceImpl implements VehicleTrendService {

    private TrendReaderApi trendReaderApi;

    @Inject
    public VehicleTrendServiceImpl(final TrendReaderApi trendReaderApi) {
        this.trendReaderApi = Validate.notNull(trendReaderApi, "The Trend Reader Api cannot be null");
    }

    @Override
    public List<VehicleTrend> getTrends(String make, Integer limit) {
        List<TrendReader> trendReaders = new ArrayList<TrendReader>();
        try {
            trendReaders = this.trendReaderApi.getTrends(make, limit);
        } catch (VehicleTrendException ex) {
            Logger.getLogger(VehicleTrendServiceImpl.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return TrendReaderUtils.convert(trendReaders);
    }

    @Override
    public VehicleTrend getTrend(final String make) {
        List<VehicleTrend> trends = this.getTrends(make, 1);
        return trends != null && trends.size() > 0 ? trends.get(0) : null;
    }

}
