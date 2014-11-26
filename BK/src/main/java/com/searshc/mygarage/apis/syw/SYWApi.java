package com.searshc.mygarage.apis.syw;

import org.springframework.stereotype.Service;

import com.searshc.mygarage.apis.syw.response.SYWUserResponse;
import com.searshc.mygarage.exceptions.SYWServiceException;

/**
 * @author Jero
 *
 */
@Service
public interface SYWApi {
	
	/**
	 * Given {@code token} we will search all information needed to the LandingPage can address the
	 * correct next path.
	 * 
	 * @param token
	 * @return
	 */
	public SYWUserResponse getUserInfoByToken(final String token) throws SYWServiceException;

}
