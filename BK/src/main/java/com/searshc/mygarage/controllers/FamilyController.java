package com.searshc.mygarage.controllers;

import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.services.FamilyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyController {

    private FamilyService familyService;

    @Autowired
    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @RequestMapping(value = "/family/{familyId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Vehicle>> getVehicles(@PathVariable("familyId") Integer familyId) {

        List<Vehicle> vehicles = this.familyService.listVehicle(familyId);

        return new ResponseEntity<List<Vehicle>>(vehicles, null, HttpStatus.OK);
    }

}
