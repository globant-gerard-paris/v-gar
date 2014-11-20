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

import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.services.user.UserService;
import com.searshc.mygarage.services.vehicle.FamilyVehicleService;

/**
 *
 * The {@link UserController} have the responsibility to manager the
 * request about the {@link User}s in the system.
 *
 * @author Jero
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private FamilyVehicleService familyVehicleService;

    /**
     * @param userService
     */
    @Inject
    public UserController(UserService userService, FamilyVehicleService familyVehicleService) {
        this.userService = Validate.notNull(userService, "The User Service cannot be null");
        this.familyVehicleService = Validate.notNull(familyVehicleService, "The User Vehicle Service cannot be null");
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable("userId") long userId) {
        User user = this.userService.getItem(userId);
        return new ResponseEntity<User>(user, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/store/{storeId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> setFavoriteStore(@PathVariable("userId") Long userId,
            @PathVariable("storeId") Long storeId) throws Exception {

        userService.setFavoriteStore(storeId, userId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/store", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getStores(@PathVariable("userId") Long userId) throws Exception {
        User user = userService.findByUserId(userId);
        if (user != null) {
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
    }
}
