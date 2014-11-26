package com.searshc.mygarage.apis.trends;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.searshc.mygarage.apis.trends.response.TrendReader;
import com.searshc.mygarage.entities.trends.VehicleTrend;


public class TrendReaderUtils {
	
	private static final Log log = LogFactory.getLog(TrendReaderUtils.class);
	
	public static VehicleTrend convert(final TrendReader trendReader) {
		VehicleTrend response = new VehicleTrend();
		try {
			response.setTitle(trendReader.getTitle());
			response.setSummary(trendReader.getSummary());
			response.setSourceUrl(trendReader.getSourceUrl());
			response.setImageUrl(trendReader.getImageUrl());
			response.setTopic(trendReader.getTopic());
		}
		catch (IllegalArgumentException e ) {
			log.error("Could not convert the TrendReader to VehicleTrend object", e);
			return null;
		} catch (NullPointerException e) {
			log.error("Could not convert the TrendReader to VehicleTrend object", e);
			return null;
		}
		return response;
		
	}
	
	public static List<VehicleTrend> convert(final List<TrendReader> trendReaders) {
		List<VehicleTrend> response = new ArrayList<VehicleTrend>();
		for(TrendReader trendReader : trendReaders) {
			try {
				response.add(convert(trendReader));
			}
			catch (Exception e) {
				log.error("Could not convert to VehicleTrend object.\nTrendReader: " + trendReader, e);
			}
		}
		return response;
	}
	
}
