package com.searshc.mygarage.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.record.SuggestedTranslation;

@Repository
public interface SuggestedTranslationRepository extends GenericRepository<SuggestedTranslation, Long> {

    /**
     * Find the {@link SuggestedTranslation} by {@code sku}.
     *
     * @param sku
     * @return return a {@link SuggestedTranslation}.
     */
    public SuggestedTranslation findBySku(@Param("sku") String sku);

}
