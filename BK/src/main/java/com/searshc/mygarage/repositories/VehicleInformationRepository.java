package com.searshc.mygarage.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.VehicleInformation;

/**
 * 
 * @author Jero
 *
 */
@Repository
public interface VehicleInformationRepository extends GenericRepository<VehicleInformation, Long> {

	/**
	 * Find the {@link VehicleInformation} by {@code userId} and {@code vehicleId}.
	 * 
	 * @param userId
	 * @return return a {@link VehicleInformation}.
	 */
	public VehicleInformation findByFamilyIdAndTangibleId(@Param("family_id") Long familyId,
			@Param("tangible_id") Long tangibleId);

}
