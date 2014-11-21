package com.searshc.mygarage.apis.trends;

import java.util.List;

import com.searshc.mygarage.apis.trends.response.TrendReader;
import com.searshc.mygarage.exceptions.VehicleTrendException;


public interface TrendReaderApi {
	
	List<TrendReader> getTrends(final String make, final Integer limit) throws VehicleTrendException;
}
