package com.searshc.mygarage.services.trends;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.apis.trends.TrendReaderApi;
import com.searshc.mygarage.apis.trends.TrendReaderUtils;
import com.searshc.mygarage.apis.trends.response.TrendReader;
import com.searshc.mygarage.entities.trends.VehicleTrend;
import com.searshc.mygarage.exceptions.VehicleTrendException;


@Service
public class VehicleTrendServiceImpl implements VehicleTrendService {

	private TrendReaderApi trendReaderApi;
	
	@Inject
	public VehicleTrendServiceImpl(final TrendReaderApi trendReaderApi) {
		this.trendReaderApi = Validate.notNull(trendReaderApi, "The Trend Reader Api cannot be null");
	}
	
	@Override
	public List<VehicleTrend> getTrends(String make) throws VehicleTrendException {
		List<TrendReader> trendReaders = this.trendReaderApi.getTrends(make);
		return TrendReaderUtils.convert(trendReaders);
	}
	
	
	@Override
	public VehicleTrend getTrend(final String make) throws VehicleTrendException {
		List<VehicleTrend> trends = this.getTrends(make);
		return trends != null && trends.size() > 0 ?  trends.get(0) : null;
	}
	
}
