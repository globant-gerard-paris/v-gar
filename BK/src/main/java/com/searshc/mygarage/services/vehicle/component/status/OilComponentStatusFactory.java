package com.searshc.mygarage.services.vehicle.component.status;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.searshc.mygarage.entities.record.ServiceRecord;

@Component
public class OilComponentStatusFactory extends AbstractComponentStatusFactory {

	private static final String COMPONENT_NAME = "Oil Change";
	
	private static final int OK_PERIOD_MONTHS_QUANTITY = 2;
	private static final int APPROACHING_DUE_MONTHS_QUANTITY = 3;
	
	@Override
	public String getComponentName() {
		return COMPONENT_NAME;
	}
	
	@Override
	public Set<String> getLocalServiceCategories() {
		Set<String> localServiceCategories = new HashSet<String>();
		localServiceCategories.add("02806672000"); //Adjust Low Oil Level
		return localServiceCategories;
	}

	@Override
	public Set<String> getRemoteServiceCategories() {
		Set<String> remoteServiceCategories = new HashSet<String>();
		remoteServiceCategories.add("49"); //Oil Change Service
		return remoteServiceCategories;
	}
	
	@Override
	public String determineStatusName(final ServiceRecord serviceRecord) {
		if(serviceRecord == null) {
			return OilStatus.LOW.name();
		}
		DateTime today = DateTime.now();
		DateTime okStartDate = today.minusMonths(OK_PERIOD_MONTHS_QUANTITY);
		DateTime dueApproachingStartDate = today.minusMonths(APPROACHING_DUE_MONTHS_QUANTITY);
		
		DateTime serviceRecordDate = new DateTime(serviceRecord.getDate());
		
		if(serviceRecordDate.isAfter(okStartDate)) {
			return OilStatus.OK.name();
		} else {
			if(serviceRecordDate.isAfter(dueApproachingStartDate)) {
				return OilStatus.MEDIUM.name();
			} else {
				return OilStatus.LOW.name();
			}
		}
	}
	
	@Override
	public String determineStatusDescription(final String statusName) {
		return OilStatus.valueOf(statusName).description;
	}
	

	public enum OilStatus {
		OK("OK"),
		MEDIUM("Maintenance approaching due"),
		LOW("Suggest oil change"),
		EMPTY("");
		
		private String description;
		
		private OilStatus(String description) {
			this.description = description;
		}
		
		public static OilStatus getBrakeStatusByDescription(final String description) {
			Validate.isTrue(!StringUtils.isEmpty(description), "The description cannot be null or empty");
			for(OilStatus status : OilStatus.values()) {
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
