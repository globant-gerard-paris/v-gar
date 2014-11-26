package com.searshc.mygarage.repositories;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.record.RecommendedServiceBlocked;

/**
 *
 * @author rammel.maestre
 */
public interface RecommendedServiceBlockedRepository extends GenericRepository<RecommendedServiceBlocked, Long> {

    Integer countByOrderNumberAndFamilyIdNumberAndTangibleIdNumberAndSku(String orderNumber,
            Long familyIdNumber, Long tangibleIdNumber, String sku);

}
