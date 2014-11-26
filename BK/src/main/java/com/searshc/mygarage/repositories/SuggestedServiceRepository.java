package com.searshc.mygarage.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.record.SuggestedService;

@Repository
public interface SuggestedServiceRepository extends GenericRepository<SuggestedService, Long> {

    /**
     * Find the {@link SuggestedService} by {@code sku}.
     *
     * @param sku
     * @return return a {@link SuggestedService}.
     */
    public SuggestedService findBySku(@Param("sku") String sku);

}
