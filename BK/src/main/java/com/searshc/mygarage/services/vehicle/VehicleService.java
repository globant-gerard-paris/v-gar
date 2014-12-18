package com.searshc.mygarage.services.vehicle;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.exceptions.VehicleNotFoundException;
import com.searshc.mygarage.repositories.VehicleRepository;

@Service
public class VehicleService extends
		GenericService<Vehicle, Long, VehicleRepository> {

	@Override
	public Vehicle getItem(final Long id) {
		Validate.notNull(id, "The VehicleId cannot be null");
		Vehicle vehicle = this.repository.findOne(id);
		if (vehicle == null) {
			throw new VehicleNotFoundException(
					"Could not find Vehicle with id: " + id);
		}
		return vehicle;
	}

	// TODO: it should include the engine since there can be two models with
	// different engines
	public Vehicle getVehicleByMakeModelAndYear(final String make,
			final String model, final int year) {
		List<Vehicle> vehicles = this.repository.getVehicleByMakeModelAndYear(
				make, model, year);
		if (vehicles != null && vehicles.size() > 0) {
			return vehicles.get(0);
		}
		return null;
	}

	public Vehicle getVehicleByMakeModelAndYearOrCreateNewVehicle(
			final String make, final String model, final int year) {
		Vehicle vehicle = this.getVehicleByMakeModelAndYear(make, model, year);
		if (vehicle == null) {
			vehicle = new Vehicle(year, make, model, null, null);
			vehicle = this.repository.saveAndFlush(vehicle);
		}
		return vehicle;
	}

	public List<String> getDistinctYears() {
		List<String> years = null;
		years = this.repository.getDistinctYears();

		if (years == null || years.size() == 0) {
			log.warn("No years where found in the vehicle entity");
			return Collections.<String>emptyList();
		}
		return years;
	}

	public List<String> getDistinctMakesByYear(final int year) {
		List<String> makes = null;
		makes = this.repository.getDistinctMakesByYear(year);
		if (makes == null || makes.size() == 0) {
			log.warn("No makes where found in the vehicle entity");
			return Collections.<String>emptyList();
		}
		return makes;
	}

	public List<String> getDistinctModelsByYearMake(final int year, final String make) {
		List<String> models = null;
		models = this.repository.getDistinctModelsByYearMake(year, make);
		if (models == null || models.size() == 0) {
			log.warn("No models where found in the vehicle entity");
			return Collections.<String>emptyList();
		}
		return models;
	}
}
