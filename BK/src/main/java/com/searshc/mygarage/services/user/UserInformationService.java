package com.searshc.mygarage.services.user;

import javax.inject.Inject;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;

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
public class UserInformationService {

	@Inject
	private UserInformationRepository repository;

	@Inject
	private StoreRepository storeRepository;

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
	 * Retrieve the {@link UserInformation} by given {@code userId} defined.
	 * 
	 * @param userId
	 * @return
	 */
	public UserInformation findByUserId(final Long userId) {
		Validate.notNull(userId, "The userId can't be null");
		return repository.findByUserId(userId);
	}

	/**
	 * First search if already exist one {@link UserInformation}, and then update or create them.
	 * 
	 * @param store
	 * @param userId
	 */
	private void processFavoriteStore(final Store store, final Long userId) {
		UserInformation information = repository.findByUserId(userId);
		if (information == null) {
			information = new UserInformation();
		}
		information.setStore(store);
		information.setUserId(userId);
		repository.saveAndFlush(information);
	}

}
