package com.searshc.mygarage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.UserVehicle;


@Repository
public interface UserVehicleRepository extends GenericRepository<UserVehicle, Long> {
	@Query("SELECT uv FROM UserVehicle uv WHERE uv.userInformation.id = :userId")
	public List<UserVehicle> getUserVehiclesByUserId(@Param("userId") final Long userId);

}
