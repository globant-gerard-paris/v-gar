package com.searshc.mygarage.services.vehicle;

import com.searshc.mygarage.dtos.carprofile.CarProfileDTO;
import com.searshc.mygarage.dtos.carprofile.LastRecallDTO;
import com.searshc.mygarage.dtos.carprofile.RecallsInformationDTO;
import com.searshc.mygarage.dtos.carprofile.VehicleDTO;
import com.searshc.mygarage.dtos.carprofile.VehicleStatusDTO;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.entities.recalls.VehicleRecalls;
import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.entities.record.ServiceCenter;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.repositories.FamilyVehicleRepository;
import com.searshc.mygarage.repositories.UserRepository;
import com.searshc.mygarage.services.nhtsa.VehicleRecallsService;
import com.searshc.mygarage.services.record.RecordService;
import java.util.Date;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author rammel.maestre
 */
@Service
public class CarProfileService {

    private final Integer MAX_SERVICE_SERVICE_RESULT = 2;
    private final String DEFAULT_VEHICLE_NAME = "My Car";

    private final Mapper mapper = new DozerBeanMapper();
    private FamilyVehicleRepository familyVehicleRepository;
    private RecordService recordService;
    private UserRepository userRepository;
    private VehicleRecallsService vehicleRecallsService;

    @Autowired
    public CarProfileService(FamilyVehicleRepository familyVehicleRepository,
            RecordService recordService, UserRepository userRepository,
            VehicleRecallsService vehicleRecallsService) {
        this.familyVehicleRepository = familyVehicleRepository;
        this.recordService = recordService;
        this.userRepository = userRepository;
        this.vehicleRecallsService = vehicleRecallsService;
    }

    public CarProfileDTO getCarProfile(Long userId, Long familyVehicleId) {
        CarProfileDTO carProfileDTO = null;
        FamilyVehicle familyVehicle = this.familyVehicleRepository.findOne(familyVehicleId);
        if (familyVehicle != null) {
            carProfileDTO = new CarProfileDTO(this.createVehicleDTO(familyVehicle));
            carProfileDTO.setVehicleStatus(this.createVehicleStatusDTO(familyVehicle));
            carProfileDTO.setServiceCenter(this.getServiceCenter(userId));
            carProfileDTO.setRecallsInformation(this.createRecallsInformationDTO(familyVehicle));
            carProfileDTO.setLastServiceHistory(this.getLastServiceRecord(familyVehicle));
            carProfileDTO.setRecommendedService(this.createRecommendedService(familyVehicle));
            this.refreshMileage(familyVehicleId, carProfileDTO);
        }
        return carProfileDTO;
    }

    private void refreshMileage(Long familyVehicleId, CarProfileDTO carProfileDTO) {
        FamilyVehicle familyVehicle = this.familyVehicleRepository.findOne(familyVehicleId);
        if (familyVehicle != null) {
            carProfileDTO.getVehicle().setMileage(familyVehicle.getMileage());
        }
    }

    private VehicleDTO createVehicleDTO(FamilyVehicle familyVehicle) {
        VehicleDTO vehicle = this.mapper.map(familyVehicle.getVehicle(), VehicleDTO.class);
        this.mapper.map(familyVehicle, vehicle);
        if (StringUtils.isEmpty(vehicle.getName())) {
            vehicle.setName(DEFAULT_VEHICLE_NAME);
        }
        return vehicle;
    }

    private VehicleStatusDTO createVehicleStatusDTO(FamilyVehicle familyVehicle) {
        VehicleStatusDTO vehicleStatus = new VehicleStatusDTO();
        vehicleStatus.setBrakesInspection("Annual inspection approaching due");
        vehicleStatus.setTireRotation("Maintenance approaching due");
        vehicleStatus.setOilChange("OK");
        return vehicleStatus;
    }

    private ServiceCenter getServiceCenter(Long userId) {
        User user = this.userRepository.getOne(userId);
        return user != null && user.getStore() != null
                ? this.mapper.map(user.getStore(), ServiceCenter.class) : null;
    }

    private LastRecallDTO createLastRecallDTO(VehicleRecalls recalls) {
        return this.mapper.map(recalls, LastRecallDTO.class);
    }

    private RecallsInformationDTO createRecallsInformationDTO(FamilyVehicle familyVehicle) {
        Vehicle vehicle = familyVehicle.getVehicle();
        RecallsInformationDTO result = null;
        if (vehicle != null) {
            List<VehicleRecalls> recalls = this.vehicleRecallsService.getRecallsOrderedByDate(vehicle.getYear(),
                    vehicle.getMake(), vehicle.getModel(), false);
            if (!recalls.isEmpty()) {
                result = new RecallsInformationDTO(recalls.size(),
                        this.createLastRecallDTO(recalls.get(0)));
            }
        }
        return result;
    }

    private List<ServiceRecord> getLastServiceRecord(FamilyVehicle familyVehicle) {
        List<ServiceRecord> result = this.recordService.getServiceRecords(familyVehicle.getId());
        return result.subList(0, Math.min(result.size(), this.MAX_SERVICE_SERVICE_RESULT));
    }

    private RecommendedService createRecommendedService(FamilyVehicle familyVehicle) {
        return this.recordService.getRecommendedServices(familyVehicle.getFamilyId(),
                familyVehicle.getTangibleId());
    }

    public void updateMileage(Long familyVehicleId, int mileage) {
        FamilyVehicle familyVehicle = this.familyVehicleRepository.findOne(familyVehicleId);
        if (familyVehicle != null) {
            familyVehicle.setMileage(mileage);
            familyVehicle.setLastMileageUpdate(new Date());
            this.familyVehicleRepository.saveAndFlush(familyVehicle);
        }
    }

}
