package com.searshc.mygarage.services.vehicle;

import com.searshc.mygarage.entities.Record;
import com.searshc.mygarage.entities.VehicleInformation;

/**
 * @author Jero
 *
 */
public interface VehicleService {

	public VehicleInformation findByFamilyIdAndTangibleId(final Long familyId, final Long tangibleId)
			throws Exception;

	public void saveRecord(final Long familyId, final Long tangibleId, final Record record)
			throws Exception;

	public void removeRecord(final Long familyId, final Long tangibleId, final Long recordId)
			throws Exception;
}
