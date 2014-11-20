package com.searshc.mygarage.services.vehicle;

import org.springframework.stereotype.Service;

import com.searshc.mygarage.base.GenericService;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.repositories.VehicleRepository;

@Service
public class VehicleService extends GenericService<Vehicle, Long, VehicleRepository> {

}
