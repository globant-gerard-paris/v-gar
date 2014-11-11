package com.searshc.mygarage.apis.nhtsa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.searshc.mygarage.apis.nhtsa.response.NHTSARecallDetails;
import com.searshc.mygarage.apis.nhtsa.response.NHTSARecalls;
import com.searshc.mygarage.entities.recalls.VehicleRecalls;


public class NHTSAUtils {
	
	private static final Log log = LogFactory.getLog(NHTSAUtils.class);
	
	public static VehicleRecalls convert(final NHTSARecallDetails nhtsaRecalls) {
		VehicleRecalls response = new VehicleRecalls();
		try {
			DateTime dateTime = NHTSAUtils.convertNHTSADateToDateTime(nhtsaRecalls.getReportReceivedDate());
			response.setReportReceivedDate(dateTime != null ? dateTime : new DateTime());
			response.setTimezone(dateTime != null ? dateTime.getZone(): DateTimeZone.forOffsetHours(0));
			response.setComponent(nhtsaRecalls.getComponent());
			response.setSummary(nhtsaRecalls.getSummary());
			response.setConsequence(nhtsaRecalls.getConsequence());
			response.setRemedy(nhtsaRecalls.getRemedy());
			response.setNotes(nhtsaRecalls.getNotes());
			response.setModelYear(Integer.valueOf(nhtsaRecalls.getModelYear()));
			response.setMake(nhtsaRecalls.getMake());
			response.setModel(nhtsaRecalls.getModel());
		}
		catch (IllegalArgumentException e ) {
			log.error("Could not convert the NHTSARecallDetails to VehicleRecall object", e);
			return null;
		} catch (NullPointerException e) {
			log.error("Could not convert the NHTSARecallDetails to VehicleRecall object", e);
			return null;
		}
		return response;
		
	}
	
	public static List<VehicleRecalls> convert(final NHTSARecalls nhtsaRecalls) {
		List<VehicleRecalls> response = new ArrayList<VehicleRecalls>();
		List<NHTSARecallDetails> nhtsaRecallsDetails = nhtsaRecalls.getNhtsaRecalls();
		for(NHTSARecallDetails nhtsaRecall : nhtsaRecallsDetails) {
			try {
				response.add(convert(nhtsaRecall));
			}
			catch (Exception e) {
				log.error("Could not convert to VehicleRecalls object.\nNHTSARecallDetails: " + nhtsaRecall, e);
			}
		}
		return response;
	}
	
	/**
	 * Converts a String to a Date object.
	 * @param nhtsaFormatDate the String with the format "/Date(123456789+0400)/"
	 * @return a DateTime object
	 */
	public static DateTime convertNHTSADateToDateTime(final String nhtsaFormatDate) {
		final int NEXT_CHAR = 1;
		DateTime response;
		
		Validate.isTrue(!StringUtils.isEmpty(nhtsaFormatDate), "The NHTSA date cannot be null");
		final int startingPos = nhtsaFormatDate.indexOf("(");
		final int endingPos = nhtsaFormatDate.indexOf(")");
		final int minusSeparatorIndex = nhtsaFormatDate.indexOf("-");
		final int plusSeparatorIndex = nhtsaFormatDate.indexOf("+");
		Validate.isTrue(startingPos >= 0 && endingPos >= 0 && endingPos - startingPos > 0 && (minusSeparatorIndex > 0 || plusSeparatorIndex > 0),
				"Incorrect format for NHTSA date: " + nhtsaFormatDate);
		final int timezoneSeparatorIndex = minusSeparatorIndex > 0 ? minusSeparatorIndex : plusSeparatorIndex; 

		//Retrieve timestamp
		String stringTimestamp = nhtsaFormatDate.substring(startingPos + NEXT_CHAR, timezoneSeparatorIndex);
		long timestamp = Long.valueOf(stringTimestamp);
		
		//Retrieve timezone offset
		String stringTimezone = nhtsaFormatDate.substring(timezoneSeparatorIndex + NEXT_CHAR, endingPos);
		int timezone = Integer.valueOf(stringTimezone) / 100;
		
		response = new DateTime(timestamp, DateTimeZone.forOffsetHours(timezone));
		return response;

	}
}
