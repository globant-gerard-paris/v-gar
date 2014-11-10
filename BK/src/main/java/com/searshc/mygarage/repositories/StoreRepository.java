package com.searshc.mygarage.repositories;

import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.Store;

/**
 * 
 * @author Jero
 *
 */
@Repository
public interface StoreRepository extends GenericRepository<Store, Long> {
	
}
