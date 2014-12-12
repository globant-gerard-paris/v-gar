package com.searshc.mygarage.services.trends;

import java.util.List;

import org.springframework.stereotype.Service;

import com.searshc.mygarage.entities.trends.VehicleTrend;

@Service
public interface VehicleTrendService {

    VehicleTrend getTrend(final String make);

    List<VehicleTrend> getTrends(final String make, final Integer limit);

}
