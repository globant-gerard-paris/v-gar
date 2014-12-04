package com.searshc.mygarage.services.vehicle;

import com.searshc.mygarage.dtos.carprofile.CarProfileDTO;
import com.searshc.mygarage.dtos.carprofile.VehicleDTO;
import com.searshc.mygarage.dtos.carprofile.VehicleStatusDTO;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.repositories.FamilyVehicleRepository;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author rammel.maestre
 */
@Service
public class CarProfileService {

    private final Mapper mapper = new DozerBeanMapper();
    private FamilyVehicleRepository familyVehicleRepository;

    public CarProfileDTO getCarProfile(Long familyVehicleId) {
        CarProfileDTO carProfileDTO = null;
        FamilyVehicle familyVehicle = this.familyVehicleRepository.findOne(familyVehicleId);
        if (familyVehicle != null) {
            carProfileDTO = new CarProfileDTO(this.createVehicleDTO(familyVehicle));
            carProfileDTO.setVehicleStatus(this.createVehicleStatusDTO(familyVehicle));
            
        }
        return carProfileDTO;
    }

    private VehicleDTO createVehicleDTO(FamilyVehicle familyVehicle) {
        VehicleDTO vehicle = this.mapper.map(familyVehicle.getVehicle(), VehicleDTO.class);
        this.mapper.map(familyVehicle, vehicle);
        return vehicle;
    }
    
    private VehicleStatusDTO createVehicleStatusDTO(FamilyVehicle familyVehicle){
        VehicleStatusDTO vehicleStatus = new VehicleStatusDTO();
        vehicleStatus.setBrakesInspection("Annual inspection approaching due");
        vehicleStatus.setTireRotation("Maintenance approaching due");
        vehicleStatus.setOilChange("OK");
        return vehicleStatus;
    }

}
