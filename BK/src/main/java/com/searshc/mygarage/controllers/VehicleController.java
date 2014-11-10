package com.searshc.mygarage.controllers;

import java.util.List;

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

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.entities.recalls.VehicleRecalls;
import com.searshc.mygarage.exceptions.NHTSARecallsException;
import com.searshc.mygarage.services.NcdbService;
import com.searshc.mygarage.services.nhtsa.VehicleRecallsService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private NcdbService ncdbService;
    private VehicleRecallsService nhtsaService;

    @Inject
    public VehicleController(final NcdbService ncdbService, final VehicleRecallsService nhtsaService) {
        this.ncdbService = Validate.notNull(ncdbService, "The NCDB Service cannot be null");
        this.nhtsaService = Validate.notNull(nhtsaService, "The NHTSA Service cannot be null");
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
    
    @RequestMapping("/recalls/year/{year}/make/{make}/model/{model}/order/{order}")
    @ResponseBody
    public ResponseEntity<List<VehicleRecalls>> getRecalls(@PathVariable("year") int year,
    		@PathVariable("make") String make,
    		@PathVariable("model") String model,
    		@PathVariable("order") String order) throws NHTSARecallsException {
    	boolean isAscendingOrder;
    	
    	if(order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("ascending")) {
    		isAscendingOrder= true;
    	} else if(order.equalsIgnoreCase("desc") || order.equalsIgnoreCase("descending")) {
    		isAscendingOrder = false;
    	} else {
    		throw new IllegalArgumentException("Invalid order. The Order must be ASC or DESC");
    	}
    	
    	List<VehicleRecalls> response = this.nhtsaService.getRecallsOrderedByDate(year, make, model, isAscendingOrder);
    	return new ResponseEntity<List<VehicleRecalls>>(response, null, HttpStatus.OK);
    }
    
    @RequestMapping("/recalls/year/{year}/make/{make}/model/{model}/last")
    @ResponseBody
    public ResponseEntity<VehicleRecalls> getRecalls(@PathVariable("year") int year,
    		@PathVariable("make") String make,
    		@PathVariable("model") String model) throws NHTSARecallsException {
    	
    	VehicleRecalls response = this.nhtsaService.getLastRecall(year, make, model);
    	return new ResponseEntity<VehicleRecalls>(response, null, HttpStatus.OK);
    }
    
    

}
