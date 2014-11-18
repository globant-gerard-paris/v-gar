package com.searshc.mygarage.services.landing;

import com.searshc.mygarage.exceptions.SYWServiceException;

/**
 * @author Jero
 *
 */
public interface LandingService {

	/**
	 * Process and return the current status of the {@link UserVehicle} against to SYW and NCDB
	 * servies.
	 * 
	 * @param token
	 * @return
	 */
	public UserVehicleStatus getUserVehicleStatus(String token) throws SYWServiceException;
}
