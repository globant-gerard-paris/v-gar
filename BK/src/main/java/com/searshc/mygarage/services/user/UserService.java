package com.searshc.mygarage.services.user;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.apis.syw.response.SYWUserResponse;
import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Store;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.exceptions.VirtualGarageServiceException;
import com.searshc.mygarage.repositories.StoreRepository;
import com.searshc.mygarage.repositories.UserRepository;

/**
 *
 * The {@link UserService} have the responsibility to update, delete and
 * retrieve stores of the system.
 *
 * @author Jero
 *
 */
@Service
public class UserService extends GenericService<User, Long, UserRepository> {

    private StoreRepository storeRepository;
    private UserRepository userRepository;

    /**
     * @param storeRepository
     */
    @Inject
    public UserService(final StoreRepository storeRepository, final UserRepository userRepository) {
        this.storeRepository = Validate.notNull(storeRepository, "The Store Repository cannot be null");
        this.userRepository = Validate.notNull(userRepository, "The User Information Repository cannot be null");
    }

    /**
     * Add new favorite {@link store} to the {@link User}.
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
    }

    ;


	/**
	 * First search if already exist one {@link User}, and then update or create them.
	 * 
	 * @param store
	 * @param userId
	 */
	private void processFavoriteStore(final Store store, final Long userId) {
        User information = userRepository.findOne(userId);
        if (information == null) {
            information = new User();
        }
        information.setSywId(1L);//FIXME: this are fixed in order to work but need to resolve this.  
        information.setStore(store);
        repository.saveAndFlush(information);
    }

    /**
     * Find the {@link User} by {@code userId}.
     *
     * @param userId
     * @return
     */
    public User findByUserId(Long userId) {
        Validate.notNull(userId, "The userId can't be null");
        return userRepository.findOne(userId);
    }
    /**
     * Find the {@link User} by {@code sywId}.
     *
     * @param userId
     * @return
     */
    public User findBySywId(Long sywId) {
    	Validate.notNull(sywId, "The sywId can't be null");
    	return userRepository.findBySywId(sywId);
    }

    
    public User createUser(User user) {
    	Validate.notNull(user, "The user can't be null.");
    	try {
    		return userRepository.saveAndFlush(user);
		} catch (Exception e) {
			log.error(e);
            throw new VirtualGarageServiceException(e);
        }
    }
    
    /**
	 * Create the user given {@code userInfoByToken} SYW information.
	 * @param userInfoByToken.
	 * @return return the user created.
	 */
	public User createUserFromSYWRespone(final SYWUserResponse userInfoByToken){
		User user = new User();
		user.setSywId(userInfoByToken.getId());
		user.setSywrMemberNumber(userInfoByToken.getSywrMemberNumber());
		return createUser(user);
	}
}
