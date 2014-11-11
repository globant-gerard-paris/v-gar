package com.searshc.mygarage.apis.nhtsa;

import com.searshc.mygarage.apis.nhtsa.response.NHTSARecalls;
import com.searshc.mygarage.exceptions.NHTSARecallsException;


public interface NHTSAApi {
	
	NHTSARecalls getRecalls(final int modelYear, final String make, final String model) throws NHTSARecallsException;
}
