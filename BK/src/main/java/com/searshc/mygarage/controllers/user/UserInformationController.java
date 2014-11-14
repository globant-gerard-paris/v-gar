package com.searshc.mygarage.controllers.user;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searshc.mygarage.entities.UserInformation;
import com.searshc.mygarage.repositories.UserInformationRepository;
import com.searshc.mygarage.services.user.UserInformationService;

/**
 * 
 * The {@link UserInformationController} have the responsibility to manager the request about the
 * {@link UserInformation}s in the system.
 * 
 * @author Jero
 *
 */
@RestController
@RequestMapping("/user")
public class UserInformationController {

	private UserInformationService userInformationService;
	
	/**
	 * @param userInformationService
	 */
	@Inject
	public UserInformationController(UserInformationService userInformationService) {
		this.userInformationService = Validate.notNull(userInformationService, "The User Information Service cannot be null");
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UserInformation> getUser(@PathVariable("userId") long userId) {
		UserInformation userInformation = this.userInformationService.getItem(userId);
		return new ResponseEntity<UserInformation>(userInformation, null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/store/{storeId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> setFavoriteStore(@PathVariable("userId") Long userId,
			@PathVariable("storeId") Long storeId) throws Exception {

		userInformationService.setFavoriteStore(storeId, userId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{userId}/store", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getStores(@PathVariable("userId") Long userId) throws Exception {
		UserInformation userInformation = userInformationService.findByUserId(userId);
		if (userInformation != null) {
			return new ResponseEntity<Object>(userInformation, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
	}
}