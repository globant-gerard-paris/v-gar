package com.searshc.mygarage.repositories;

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
}
