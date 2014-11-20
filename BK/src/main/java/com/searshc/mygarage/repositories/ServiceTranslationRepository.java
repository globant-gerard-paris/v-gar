package com.searshc.mygarage.repositories;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.ServiceTranslation;

@Repository
public interface ServiceTranslationRepository extends GenericRepository<ServiceTranslation, Long> {

    /**
     * Find the {@link ServiceTranslation} by {@code productFlag}.
     *
     * @param productFlag
     * @return return a {@link ServiceTranslation}.
     */
    public ServiceTranslation findByProductFlag(@Param("product_flag") Integer productFlag);

}
