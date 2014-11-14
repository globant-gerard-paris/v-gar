package com.searshc.mygarage.services.user;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Store;
import com.searshc.mygarage.entities.UserInformation;
import com.searshc.mygarage.repositories.StoreRepository;
import com.searshc.mygarage.repositories.UserInformationRepository;

/**
 * 
 * The {@link UserInformationService} have the responsibility to update, delete and retrieve stores
 * of the system.
 * 
 * @author Jero
 *
 */
@Service
public class UserInformationService extends GenericService<UserInformation, Long, UserInformationRepository>{

	private StoreRepository storeRepository;
	private  UserInformationRepository userInformationRepository;
	
	

	/**
	 * @param storeRepository
	 */
	@Inject
	public UserInformationService(StoreRepository storeRepository, UserInformationRepository userInformationRepository) {
		this.storeRepository = Validate.notNull(storeRepository, "The Store Repository cannot be null");
		this.userInformationRepository = Validate.notNull(userInformationRepository, "The User Information Repository cannot be null");
	}

	/**
	 * Add new favorite {@link store} to the {@link UserInformation}.
	 * 
	 * @param storeId
	 * @param userId
	 * @throws Exception
	 */
	public void setFavoriteStore(final Long storeId, final Long userId) throws Exception {
		Validate.notNull(storeId, "The storeId can't be null");
		Validate.notNull(userId, "The userId can't be null");

		Store store = storeRepository.findOne(storeId);
		Validate.notNull(store, "The storeId " + storeId + " doesn't exist.");

		try {
			processFavoriteStore(store, userId);
		} catch (Exception e) {
			throw new Exception("An error occured when trying to persist the registry.", e);
		}
	};


	/**
	 * First search if already exist one {@link UserInformation}, and then update or create them.
	 * 
	 * @param store
	 * @param userId
	 */
	private void processFavoriteStore(final Store store, final Long userId) {
		UserInformation information = userInformationRepository.findByUserId(userId);
		if (information == null) {
			information = new UserInformation();
		}
		information.setSywId(1L);//FIXME: this are fixed in order to work but need to resolve this.  
		information.setStore(store);
		information.setUserId(userId);
		repository.saveAndFlush(information);
	}

	/**
	 * Find the {@link UserInformation} by {@code userId}.
	 * @param userId
	 * @return
	 */
	public UserInformation findByUserId(Long userId) {
		Validate.notNull(userId, "The userId can't be null");
		return userInformationRepository.findByUserId(userId);
	}

}
