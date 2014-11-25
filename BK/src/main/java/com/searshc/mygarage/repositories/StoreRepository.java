package com.searshc.mygarage.repositories;

import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.Store;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Jero
 *
 */
@Repository
public interface StoreRepository extends GenericRepository<Store, Long> {

    /**
     * Find the {@link Store} by {@code sactStore}.
     *
     * @param sacStore
     * @return return a {@link Store}.
     */
    public Store findBySacStore(@Param("sac_store") String sacStore);

}
