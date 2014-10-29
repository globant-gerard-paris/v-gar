package com.searshc.mygarage.services;

import com.searshc.mygarage.entities.Vehicle;
import java.util.List;

public interface FamilyService {

    List<Vehicle> listVehicle(Integer familyId);

}
