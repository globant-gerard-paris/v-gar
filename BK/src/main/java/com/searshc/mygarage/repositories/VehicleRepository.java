package com.searshc.mygarage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.Vehicle;

/**
 *
 * @author Jero
 *
 */
@Repository
public interface VehicleRepository extends GenericRepository<Vehicle, Long> {

	//TODO: it should include the engine since there can be two models with different engines
	@Query("SELECT v FROM Vehicle v WHERE v.make = :make AND v.model = :model AND v.year = :year")
	public List<Vehicle> getVehicleByMakeModelAndYear(@Param("make") final String make, @Param("model") final String model, @Param("year") final int year);
}
