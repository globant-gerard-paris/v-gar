package com.searshc.mygarage.controllers;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.services.CarService;

@RestController
public class CarController {

    private CarService carService;

    @Inject
    public CarController(final CarService carService) {
        Validate.notNull(carService, "The CarService cannot be null");
        this.carService = carService;
    }

    @RequestMapping("/")
    String home() {
        return "It works";
    }

    @RequestMapping(value = "/family/{familyId}/vehicle/{vehicleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Order>> getCarTransactionsHistory(@PathVariable("familyId") Integer familyId,
            @PathVariable("vehicleId") Integer vehicleId) {
        List<Order> orders = this.carService.getTransactions(familyId, vehicleId);
        return new ResponseEntity<List<Order>>(orders, null, HttpStatus.OK);
    }

}
