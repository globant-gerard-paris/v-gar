package com.searshc.mygarage.services.vehicle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.repositories.VehicleRepository;

@Service
public class VehicleService extends GenericService<Vehicle, Long, VehicleRepository> {

	//TODO: it should include the engine since there can be two models with different engines
	public Vehicle getVehicleByMakeModelAndYear(final String make, final String model, final int year) {
		List<Vehicle> vehicles = this.repository.getVehicleByMakeModelAndYear(make, model, year);
		if(vehicles != null && vehicles.size() > 0) {
			return vehicles.get(0);
		}
		return null;
	}
}
