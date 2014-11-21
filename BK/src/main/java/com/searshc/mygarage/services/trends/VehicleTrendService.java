package com.searshc.mygarage.services.trends;

import java.util.List;

import org.springframework.stereotype.Service;

import com.searshc.mygarage.entities.trends.VehicleTrend;
import com.searshc.mygarage.exceptions.VehicleTrendException;


@Service
public interface VehicleTrendService {
	
	VehicleTrend getTrend(final String make) throws VehicleTrendException;
	
	List<VehicleTrend> getTrends(final String make, final Integer limit) throws VehicleTrendException;

	
}
