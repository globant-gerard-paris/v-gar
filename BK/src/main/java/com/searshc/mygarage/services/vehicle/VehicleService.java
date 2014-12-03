package com.searshc.mygarage.services.vehicle;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.exceptions.VehicleNotFoundException;
import com.searshc.mygarage.repositories.VehicleRepository;

@Service
public class VehicleService extends GenericService<Vehicle, Long, VehicleRepository> {

	@Override
	public Vehicle getItem(final Long id) {
		Validate.notNull(id, "The VehicleId cannot be null");
		Vehicle vehicle = this.repository.findOne(id);
		if(vehicle == null) {
			throw new VehicleNotFoundException("Could not find Vehicle with id: " + id);
		}
		return vehicle;
	}
	
	//TODO: it should include the engine since there can be two models with different engines
	public Vehicle getVehicleByMakeModelAndYear(final String make, final String model, final int year) {
		List<Vehicle> vehicles = this.repository.getVehicleByMakeModelAndYear(make, model, year);
		if(vehicles != null && vehicles.size() > 0) {
			return vehicles.get(0);
		}
		return null;
	}
	
	public Vehicle getVehicleByMakeModelAndYearOrCreateNewVehicle(final String make, final String model, final int year) {
		Vehicle vehicle = this.getVehicleByMakeModelAndYear(make, model, year);
		if(vehicle == null) {
			vehicle = new Vehicle(year, make, model, null, null);
			vehicle = this.repository.saveAndFlush(vehicle);
		}
		return vehicle;
	}
}
