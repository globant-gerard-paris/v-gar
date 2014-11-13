package com.searshc.mygarage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.Vehicle;

@Repository
public interface VehicleRepository extends GenericRepository<Vehicle, Long> {

	@Query("SELECT v FROM Vehicle v WHERE v.familyId = :familyId")
	List<Vehicle> getVehiclesByFamilyId(@Param("familyId") Long familyId);
	
	@Query("SELECT v FROM UserVehicle uv INNER JOIN uv.vehicle v WHERE uv.userInformation.id = :userId and uv.isConfirmed = true")
	List<Vehicle> getVehiclesByUserId(@Param("userId") Long userId);
	
}
