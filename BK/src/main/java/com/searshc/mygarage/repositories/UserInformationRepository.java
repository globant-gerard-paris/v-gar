package com.searshc.mygarage.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.UserInformation;
import com.searshc.mygarage.entities.VehicleInformation;

/**
 * 
 * @author Jero
 *
 */
@Repository
public interface UserInformationRepository extends GenericRepository<UserInformation, Long> {
	
	/**
	 * Find the {@link UserInformationRepository} by {@code userId}.
	 * 
	 * @param userId
	 * @return return a {@link VehicleInformation}.
	 */
	public UserInformation findByUserId(@Param("user_id") Long userId);
}
