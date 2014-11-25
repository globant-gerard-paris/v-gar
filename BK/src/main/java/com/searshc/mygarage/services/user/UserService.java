package com.searshc.mygarage.services.user;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import scala.collection.mutable.StringBuilder;

import com.searshc.mygarage.apis.syw.SYWApi;
import com.searshc.mygarage.apis.syw.SYWUtils;
import com.searshc.mygarage.apis.syw.response.SYWUserResponse;
import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Store;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.exceptions.UserNotFoundException;
import com.searshc.mygarage.exceptions.VirtualGarageServiceException;
import com.searshc.mygarage.repositories.StoreRepository;
import com.searshc.mygarage.repositories.UserRepository;
import com.searshc.mygarage.services.ncdb.NCDBLocalService;

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

	private static final Log log = LogFactory.getLog(UserService.class);
	
    private StoreRepository storeRepository;
    private UserRepository userRepository;
    private SYWApi sywApi;
	private NCDBLocalService ncdbLocal;
    /**
     * @param storeRepository
     */
    @Inject
    public UserService(final StoreRepository storeRepository, final UserRepository userRepository, final SYWApi sywApi, final NCDBLocalService ncdbLocal) {
        this.storeRepository = Validate.notNull(storeRepository, "The Store Repository cannot be null");
        this.userRepository = Validate.notNull(userRepository, "The User Information Repository cannot be null");
        this.sywApi = Validate.notNull(sywApi, "The sywApi cannot be null");
        this.ncdbLocal = Validate.notNull(ncdbLocal, "The NCDB local service cannot be null.");
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


    public User processUserByToken(final String token){
    	Validate.notNull(token, "The token can't be null.");

		Long sywId = SYWUtils.getSywId(token);
		Validate.notNull(sywId, "The token " + token + " is not valid.");

		User user = findBySywId(sywId);
		if (user == null) {
			SYWUserResponse userInfoByToken = sywApi.getUserInfoByToken(token);
			Validate.notNull(userInfoByToken, "Not found user on SYW service with token: " + token);
			Validate.notNull(userInfoByToken.getSywrMemberNumber(), "The user not have shopyourway member number: " + token);
			user = toUser(userInfoByToken);
			if (user.getFamilyId() == null) {
				String familyId = ncdbLocal.getNcdbIdBySywMemberNumber(user.getSywrMemberNumber());
				user = assignFamilyId(user, familyId);
			}
			saveUser(user);
		} else if (user.getFamilyId() == null) {
			String familyId = ncdbLocal.getNcdbIdBySywMemberNumber(user.getSywrMemberNumber());
			user = assignFamilyId(user, familyId);
			saveUser(user);
		}
		return user;
    }
    

	private User assignFamilyId(final User user, final String ncdbId) {
		if (!StringUtils.isEmpty(ncdbId)) {
			user.setFamilyId(Long.valueOf(ncdbId));
		}
		return user;
	}
    
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

	@Override
	public User getItem(Long id) {
		Validate.notNull(id, "The userId can't be null");
        User user = userRepository.findOne(id);
        if (user == null) {
        	String msg = new StringBuilder().append("No user found with id: ").append(id).toString();
        	log.error(msg);
        	throw new UserNotFoundException(msg);
        }
        return user;
	}
	
    /**
     * Find the {@link User} by {@code userId}.
     *
     * @param userId
     * @return
     */
    public User findByUserId(Long userId) {
        return this.getItem(userId);
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

    
    public User saveUser(User user) {
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
	public User toUser(final SYWUserResponse userInfoByToken){
		User user = new User();
		user.setSywId(userInfoByToken.getId());
		user.setSywrMemberNumber(userInfoByToken.getSywrMemberNumber());
		return user;
	}
}
