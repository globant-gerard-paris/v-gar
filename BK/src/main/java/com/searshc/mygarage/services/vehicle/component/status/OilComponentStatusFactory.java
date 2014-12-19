package com.searshc.mygarage.services.vehicle.component.status;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.searshc.mygarage.entities.record.ServiceRecord;

@Component
public class OilComponentStatusFactory extends AbstractComponentStatusFactory {

	
	@Value("#{'${carprofile.infographics.vehicle.component.oil.local.service.codes}'.split(',')}")
	private Set<String> localServiceCategories;
	@Value("#{'${carprofile.infographics.vehicle.component.oil.ncdb.service.codes}'.split(',')}")
	private Set<String> remoteServiceCategories;
	
	private static final String COMPONENT_NAME = "Oil Change";
	
	private static final int OK_PERIOD_MONTHS_QUANTITY = 2;
	private static final int APPROACHING_DUE_MONTHS_QUANTITY = 3;
	
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
