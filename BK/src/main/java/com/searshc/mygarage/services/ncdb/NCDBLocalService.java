package com.searshc.mygarage.services.ncdb;

import org.springframework.stereotype.Service;

/**
 * @author Jero
 *
 */
@Service
public interface NCDBLocalService {

    public String getNcdbIdBySywMemberNumber(String sywId);
}
