package com.searshc.mygarage.repositories;

import org.springframework.stereotype.Repository;

import com.searshc.mygarage.base.GenericRepository;
import com.searshc.mygarage.entities.Vehicle;

/**
 *
 * @author Jero
 *
 */
@Repository
public interface VehicleRepository extends GenericRepository<Vehicle, Long> {

}
