package com.searshc.mygarage.services.ncdb;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * @author Jero
 *
 */
@Service
public class NCDBLocalServiceImpl implements NCDBLocalService {

	private static final Log LOG = LogFactory.getLog(NCDBLocalServiceImpl.class);
	
	@Inject
	private EntityManager em;

	public String getNcdbIdBySywMemberNumber(String sywId) {
		try {
			Query createNativeQuery = em.createNativeQuery("SELECT ncdbid FROM sywrncdbidmapping WHERE sywrid='"+sywId+"'");
			List user = createNativeQuery.getResultList();
			if(CollectionUtils.isEmpty(user)){
				return null;
			}
			return ((String) user.get(0)).trim();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			LOG.error("An error ocurre when retrieve SYW_ID: "+sywId+ " of mapping table.", e);
			return null;
		}
	}
}
