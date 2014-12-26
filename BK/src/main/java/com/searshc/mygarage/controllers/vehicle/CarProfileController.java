package com.searshc.mygarage.controllers.vehicle;

import com.searshc.mygarage.dtos.carprofile.BlockSuggestedServiceDTO;
import com.searshc.mygarage.dtos.carprofile.CarProfileDTO;
import com.searshc.mygarage.services.vehicle.CarProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rammel.maestre
 */
@RestController
@RequestMapping("/car-profile")
public class CarProfileController {

    private CarProfileService carProfileService;

    @Autowired
    public CarProfileController(CarProfileService carProfileService) {
        this.carProfileService = carProfileService;
    }

    @RequestMapping(value = "/user/{userId}/familyvehicle/{familyVehicleId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CarProfileDTO> getCarProfile(
            @PathVariable("userId") Long userId,
            @PathVariable("familyVehicleId") Long familyVehicleId) {
        CarProfileDTO carProfileDTO = this.carProfileService.getCarProfile(userId, familyVehicleId);
        return new ResponseEntity<CarProfileDTO>(carProfileDTO, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/familyvehicle/{familyVehicleId}/mileage",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> updateMileage(
            @PathVariable("familyVehicleId") Long familyVehicleId,
            @RequestBody Integer mileage) {
        this.carProfileService.updateMileage(familyVehicleId, mileage);
        return new ResponseEntity<Object>(null, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/familyvehicle/{familyVehicleId}/suggested",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> blockSuggestedService(
            @PathVariable("familyVehicleId") Long familyVehicleId,
            @RequestBody BlockSuggestedServiceDTO block) {
        this.carProfileService.blockSuggestedService(familyVehicleId, block.getOrderNumber(), block.getCode());
        return new ResponseEntity<Object>(null, null, HttpStatus.OK);
    }

}
