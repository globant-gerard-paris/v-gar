package com.searshc.mygarage.services.vehicle.component.status;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.searshc.mygarage.entities.record.ServiceRecord;

@Component
public class BrakeComponentStatusFactory extends AbstractComponentStatusFactory {

	@Value("#{'${carprofile.infographics.vehicle.component.brake.local.service.codes}'.split(',')}")
	private Set<String> localServiceCategories;
	@Value("#{'${carprofile.infographics.vehicle.component.brake.ncdb.service.codes}'.split(',')}")
	private Set<String> remoteServiceCategories;
	
	private static final String COMPONENT_NAME = "Brakes Inspection";
	
	private static final int OK_PERIOD_MONTHS_QUANTITY = 10;
	private static final int APPROACHING_DUE_MONTHS_QUANTITY = 11;
	
	@Override
	public String getComponentName() {
		return COMPONENT_NAME;
	}
	
	@Override
	public Set<String> getLocalServiceCategories() {
		return localServiceCategories;
	}

	@Override
	public Set<String> getRemoteServiceCategories() {
		return remoteServiceCategories;
	}
	
	@Override
	public String determineStatusName(final ServiceRecord serviceRecord) {
		if(serviceRecord == null) {
			return BrakeStatus.LOW.name(); 
		}
		DateTime today = DateTime.now();
		DateTime okStartDate = today.minusMonths(OK_PERIOD_MONTHS_QUANTITY);
		DateTime dueApproachingStartDate = today.minusMonths(APPROACHING_DUE_MONTHS_QUANTITY);
		
		DateTime serviceRecordDate = new DateTime(serviceRecord.getDate());
		
		if(serviceRecordDate.isAfter(okStartDate)) {
			return BrakeStatus.FULL.name();
		} else {
			if(serviceRecordDate.isAfter(dueApproachingStartDate)) {
				return BrakeStatus.MEDIUM.name();
			} else {
				return BrakeStatus.LOW.name();
			}
		}
	}
	
	@Override
	public String determineStatusDescription(final String statusName) {
		return BrakeStatus.valueOf(statusName).description;
	}
	

	public enum BrakeStatus {
		FULL("OK"),
		MEDIUM("Annual inspection approaching due"),
		LOW("Suggest annual inspection"),
		EMPTY("");
		
		private String description;
		
		private BrakeStatus(String description) {
			this.description = description;
		}
		
		public static BrakeStatus getBrakeStatusByDescription(final String description) {
			Validate.isTrue(!StringUtils.isEmpty(description), "The description cannot be null or empty");
			for(BrakeStatus status : BrakeStatus.values()) {
				if(status.description.equalsIgnoreCase(description)) {
					return status;
				}
			}
			return null;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		
	}

}
