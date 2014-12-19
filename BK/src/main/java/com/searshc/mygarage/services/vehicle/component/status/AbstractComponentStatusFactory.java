package com.searshc.mygarage.services.vehicle.component.status;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.searshc.mygarage.dtos.carprofile.component.VehicleComponentStatusDTO;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.entities.record.ServiceRecordItem;
import com.searshc.mygarage.util.ServiceRecordType;

public abstract class AbstractComponentStatusFactory {

	public VehicleComponentStatusDTO createComponentStatus(final List<ServiceRecord> localAndRemotesServiceRecordsdAndOrderedByDate) {
		ServiceRecord serviceRecord = null;
		String statusName;
		if(!CollectionUtils.isEmpty(localAndRemotesServiceRecordsdAndOrderedByDate)) {
			Set<String> localServiceCategories = this.getLocalServiceCategories();
			Set<String> remoteServiceCategories = this.getRemoteServiceCategories();

			serviceRecord = this.getLastServiceRecordFilteredByCategory(
					localAndRemotesServiceRecordsdAndOrderedByDate, localServiceCategories, remoteServiceCategories);

			statusName = this.determineStatusName(serviceRecord);
		} else {
			statusName = "EMPTY";
		}
		String componentName = this.getComponentName();
		String statusDescription = this.determineStatusDescription(statusName);
		
		
		return new VehicleComponentStatusDTO(componentName, statusName, statusDescription);
	}
	
	public abstract String getComponentName();
	public abstract Set<String> getLocalServiceCategories();
	public abstract Set<String> getRemoteServiceCategories();
	public abstract String determineStatusName(final ServiceRecord serviceRecord);
	public abstract String determineStatusDescription(final String statusName);
	
	public ServiceRecord getLastServiceRecordFilteredByCategory(List<ServiceRecord> serviceRecords,
			final Set<String> localServiceCategories, final Set<String> remoteServiceCategories) {
		

		List<ServiceRecordItem> items = null;
		for(ServiceRecord serviceRecord : serviceRecords) {
			items = serviceRecord.getServiceRecordItems();
			for(ServiceRecordItem item : items) {
				if(serviceRecord.getType().equals(ServiceRecordType.LOCAL_SERVICE)) {
					//Filter by categories using Sku from SuggestedService
					if(localServiceCategories.contains(item.getCode())) {
						return serviceRecord;
					}
				} else {
					//Filter by categories using ProductFlag from ServiceTranslation
					if(remoteServiceCategories.contains(item.getCode())) {
						return serviceRecord;
					}
				}
			}
		}
		return null;
	}
}
