package com.searshc.mygarage.services.nhtsa;

import java.util.List;

import org.springframework.stereotype.Service;

import com.searshc.mygarage.entities.recalls.VehicleRecalls;
import com.searshc.mygarage.exceptions.NHTSARecallsException;


@Service
public interface VehicleRecallsService {
	
	List<VehicleRecalls> getRecalls(final int year, final String make, final String model) throws NHTSARecallsException;
	
	List<VehicleRecalls> getRecallsOrderedByDate(final int year, final String make,
			final String model, final boolean isAscending) throws NHTSARecallsException;
	
	VehicleRecalls getLastRecall(final int year, final String make,
			final String model) throws NHTSARecallsException;
	
}
