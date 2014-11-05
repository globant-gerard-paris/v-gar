package com.searshc.mygarage.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.AdditionalVehicle;
import com.searshc.mygarage.repositories.AdditionalVehicleRepository;

@Service
@Transactional
public class AdditionalVehicleService extends GenericService<AdditionalVehicle, Integer, AdditionalVehicleRepository>{

	public AdditionalVehicle createAndSaveNewAdditionalVehicle(final String make, final String model,
			final int year, final String color, final int mileage) {
		AdditionalVehicle vehicle = new AdditionalVehicle(0L, make, model, year, color, mileage);
		return super.save(vehicle);
		
	}
}
