package com.searshc.mygarage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.UserVehicle;

@Repository
public interface UserVehicleRepository extends GenericRepository<UserVehicle, Long> {

	@Query("SELECT uv FROM UserVehicle uv WHERE uv.familyId = :familyId")
	List<UserVehicle> getUserVehiclesByFamilyId(@Param("familyId") Long familyId);
	
	@Query("SELECT uv FROM ConfirmedVehicle cv INNER JOIN cv.userVehicle uv WHERE cv.user.id = :userId")
	List<UserVehicle> getUserVehiclesByUserId(@Param("userId") Long userId);
	
	@Query("SELECT uv FROM UserVehicle uv where uv.tangibleId = :tangibleId")
	UserVehicle getUserVehicleByTangibleId(@Param("tangibleId")final Long tangibleId);
}
