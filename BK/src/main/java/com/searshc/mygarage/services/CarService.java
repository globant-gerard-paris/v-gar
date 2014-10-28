package com.searshc.mygarage.services;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.entities.Order;

@Service
public class CarService {

	
	private NCDBApi ncdbApi;
	
	@Inject
	public CarService(final NCDBApi ncdbApi) {
		Validate.notNull(ncdbApi, "The NCDBApi cannot be null");
		this.ncdbApi = ncdbApi;
	}
	
	public List<Order> getTransactions(final String familyIdNumber, final String tangibleId) {
		//TODO: query the ncdb service
		//TODO: query the local database
		//TODO: merge both results
		return this.ncdbApi.getCarTransactionHistory(familyIdNumber, tangibleId);
	}
	
}
