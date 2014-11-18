package com.searshc.mygarage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.ConfirmedVehicle;


@Repository
public interface ConfirmedVehicleRepository extends GenericRepository<ConfirmedVehicle, Long> {
	@Query("SELECT cv FROM ConfirmedVehicle cv WHERE cv.user.id = :userId")
	public List<ConfirmedVehicle> getConfirmedVehiclesByUserId(@Param("userId") final Long userId);

}
