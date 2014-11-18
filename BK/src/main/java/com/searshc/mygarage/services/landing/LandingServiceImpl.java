package com.searshc.mygarage.services.landing;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.apis.syw.SYWApi;
import com.searshc.mygarage.apis.syw.response.SYWUserResponse;
import com.searshc.mygarage.exceptions.SYWServiceException;
import com.searshc.mygarage.services.UserVehicleService;

/**
 * @author Jero
 *
 */
@Service
public class LandingServiceImpl implements LandingService {

	@Inject
	private SYWApi sywApi;

	@Inject
	private NCDBApi ncdbApi;
	
	@Inject
	private UserVehicleService userVehicleService;
	
	@Override
	public UserVehicleStatus getUserVehicleStatus(String token) throws SYWServiceException {
		
		SYWUserResponse userInfoByToken = sywApi.getUserInfoByToken(token);
		String ncdbId = ncdbApi.getLocalNcdbIdBySywId(userInfoByToken.getSywrMemberNumber());
		
		if(StringUtils.isEmpty(ncdbId)){
			//Buscar en user vehicle y obtener cuales tiene si es que tiene agregados o no.
			
		}else{
			//
		}
		
		return null;
	}
	
}
