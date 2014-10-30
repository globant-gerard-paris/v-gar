package com.searshc.mygarage.controllers;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.services.NcdbService;
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
public class NcdbController {

    private NcdbService ncdbService;

    @Autowired
    public NcdbController(NcdbService ncdbService) {
        this.ncdbService = ncdbService;
    }

    @RequestMapping(value = "/vehicles/{ncdbId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Vehicle>> getVehicles(@PathVariable("ncdbId") Integer ncdbId) {
        List<Vehicle> vehicles = this.ncdbService.listVehicles(ncdbId);
        return new ResponseEntity<List<Vehicle>>(vehicles, null, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicles/{ncdbId}/transactions/{vehicleId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Order>> getCarTransactionsHistory(@PathVariable("ncdbId") Integer ncdbId,
            @PathVariable("vehicleId") Integer vehicleId) {
        List<Order> orders = this.ncdbService.getTransactions(ncdbId, vehicleId);
        return new ResponseEntity<List<Order>>(orders, null, HttpStatus.OK);
    }

}
