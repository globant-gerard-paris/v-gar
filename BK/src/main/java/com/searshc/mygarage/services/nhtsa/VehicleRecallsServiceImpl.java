package com.searshc.mygarage.services.nhtsa;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.apis.nhtsa.NHTSAApi;
import com.searshc.mygarage.apis.nhtsa.NHTSAUtils;
import com.searshc.mygarage.apis.nhtsa.response.NHTSARecalls;
import com.searshc.mygarage.entities.recalls.VehicleRecalls;
import com.searshc.mygarage.exceptions.NHTSARecallsException;


@Service
public class VehicleRecallsServiceImpl implements VehicleRecallsService {

	private NHTSAApi nhtsaApi;
	
	@Inject
	public VehicleRecallsServiceImpl(final NHTSAApi nhtsaApi) {
		this.nhtsaApi = Validate.notNull(nhtsaApi, "The NHTSA Api cannot be null");
	}
	
	@Override
	public List<VehicleRecalls> getRecalls(int year, String make, String model) throws NHTSARecallsException {
		NHTSARecalls nhtsaRecalls = this.nhtsaApi.getRecalls(year, make, model);
		return NHTSAUtils.convert(nhtsaRecalls);
	}
	
	@Override
	public List<VehicleRecalls> getRecallsOrderedByDate(final int year, final String make,
			final String model, final boolean isAscending) throws NHTSARecallsException {
		List<VehicleRecalls> recalls = this.getRecalls(year, make, model);
		Collections.sort(recalls, new Comparator<VehicleRecalls>() {

			@Override
			public int compare(VehicleRecalls o1, VehicleRecalls o2) {
				int multiplierFactor = isAscending == true ? 1 : -1;
				return o1.getReportReceivedDate().compareTo(o2.getReportReceivedDate()) * multiplierFactor;
			}});
		return recalls;
	}
	
	@Override
	public VehicleRecalls getLastRecall(final int year, final String make,
			final String model) throws NHTSARecallsException {
		List<VehicleRecalls> recalls = this.getRecallsOrderedByDate(year, make, model, false);
		return recalls != null && recalls.size() > 0 ?  recalls.get(0) : null;
	}
	
}
