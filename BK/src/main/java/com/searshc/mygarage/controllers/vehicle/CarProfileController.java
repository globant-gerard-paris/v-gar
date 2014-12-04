package com.searshc.mygarage.controllers.vehicle;

import com.searshc.mygarage.dtos.carprofile.CarProfileDTO;
import com.searshc.mygarage.services.vehicle.CarProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/{familyVehicleId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CarProfileDTO> getCarProfile(
            @PathVariable("{familyVehicleId}") Long familyVehicleId) {

        CarProfileDTO carProfileDTO = this.carProfileService.getCarProfile(familyVehicleId);

        return new ResponseEntity<CarProfileDTO>(carProfileDTO, null, HttpStatus.OK);
    }

}
