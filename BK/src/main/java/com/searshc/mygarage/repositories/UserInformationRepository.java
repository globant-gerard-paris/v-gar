package com.searshc.mygarage.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.UserInformation;

/**
 * 
 * @author Jero
 *
 */
@Repository
public interface UserInformationRepository extends GenericRepository<UserInformation, Long> {

	/**
	 * Find the {@link UserInformation} by {@code userId}.
	 * 
	 * @param userId
	 * @return return a {@link UserInformation}.
	 */
	public UserInformation findByUserId(@Param("user_id") Long userId);

}
