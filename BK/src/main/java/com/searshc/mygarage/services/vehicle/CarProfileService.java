package com.searshc.mygarage.services.vehicle;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.searshc.mygarage.dtos.carprofile.CarProfileDTO;
import com.searshc.mygarage.dtos.carprofile.LastRecallDTO;
import com.searshc.mygarage.dtos.carprofile.RecallsInformationDTO;
import com.searshc.mygarage.dtos.carprofile.VehicleDTO;
import com.searshc.mygarage.dtos.carprofile.component.VehicleComponentStatusDTO;
import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.entities.Vehicle;
import com.searshc.mygarage.entities.recalls.VehicleRecalls;
import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.entities.record.RecommendedServiceBlocked;
import com.searshc.mygarage.entities.record.ServiceCenter;
import com.searshc.mygarage.entities.record.ServiceRecord;
import com.searshc.mygarage.repositories.FamilyVehicleRepository;
import com.searshc.mygarage.repositories.RecommendedServiceBlockedRepository;
import com.searshc.mygarage.repositories.UserRepository;
import com.searshc.mygarage.services.nhtsa.VehicleRecallsService;
import com.searshc.mygarage.services.record.RecordService;
import com.searshc.mygarage.services.vehicle.component.status.BrakeComponentStatusFactory;
import com.searshc.mygarage.services.vehicle.component.status.OilComponentStatusFactory;
import com.searshc.mygarage.services.vehicle.component.status.TireComponentStatusFactory;
import java.util.ArrayList;

/**
 *
 * @author rammel.maestre
 */
@Service
public class CarProfileService {

    private final Integer MAX_SERVICE_SERVICE_RESULT = 2;

    private final Mapper mapper = new DozerBeanMapper();
    private FamilyVehicleRepository familyVehicleRepository;
    private RecordService recordService;
    private UserRepository userRepository;
    private VehicleRecallsService vehicleRecallsService;
    private BrakeComponentStatusFactory brakeComponentStatusFactory;
    private TireComponentStatusFactory tireComponentStatusFactory;
    private OilComponentStatusFactory oilComponentStatusFactory;
    private RecommendedServiceBlockedRepository recommendedServiceBlockedRepository;

    @Autowired
    public CarProfileService(FamilyVehicleRepository familyVehicleRepository,
            RecordService recordService, UserRepository userRepository,
            VehicleRecallsService vehicleRecallsService,
            BrakeComponentStatusFactory brakeComponentStatusFactory,
            TireComponentStatusFactory tireComponentStatusFactory,
            OilComponentStatusFactory oilComponentStatusFactory,
            RecommendedServiceBlockedRepository recommendedServiceBlockedRepository) {
        this.familyVehicleRepository = familyVehicleRepository;
        this.recordService = recordService;
        this.userRepository = userRepository;
        this.vehicleRecallsService = vehicleRecallsService;
        this.brakeComponentStatusFactory = brakeComponentStatusFactory;
        this.tireComponentStatusFactory = tireComponentStatusFactory;
        this.oilComponentStatusFactory = oilComponentStatusFactory;
        this.recommendedServiceBlockedRepository = recommendedServiceBlockedRepository;
    }

    public CarProfileDTO getCarProfile(Long userId, Long familyVehicleId) {
        CarProfileDTO carProfileDTO = null;
        FamilyVehicle familyVehicle = this.familyVehicleRepository.findOne(familyVehicleId);
        if (familyVehicle != null) {
            carProfileDTO = new CarProfileDTO(this.createVehicleDTO(familyVehicle));
            carProfileDTO.setServiceCenter(this.getServiceCenter(userId));
            carProfileDTO.setRecallsInformation(this.createRecallsInformationDTO(familyVehicle));
            carProfileDTO.setRecommendedService(this.createRecommendedService(familyVehicle));
            List<ServiceRecord> mixedRecordsOrderedByDate = this.recordService.getServiceRecords(familyVehicle.getId());
            carProfileDTO.setLastServiceHistory(this.getLastServiceRecord(mixedRecordsOrderedByDate));
            this.refreshMileage(familyVehicleId, carProfileDTO);
            carProfileDTO.setVehicleStatus(this.createVehicleStatusDTO(mixedRecordsOrderedByDate));
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
        return vehicle;
    }

    private List<VehicleComponentStatusDTO> createVehicleStatusDTO(List<ServiceRecord> mixedRecordsOrderedByDate) {
        VehicleComponentStatusDTO tires = this.tireComponentStatusFactory.createComponentStatus(mixedRecordsOrderedByDate);
        VehicleComponentStatusDTO oil = this.oilComponentStatusFactory.createComponentStatus(mixedRecordsOrderedByDate);
        VehicleComponentStatusDTO brakes = this.brakeComponentStatusFactory.createComponentStatus(mixedRecordsOrderedByDate);

        List<VehicleComponentStatusDTO> vehicleStatus = new ArrayList<VehicleComponentStatusDTO>();
        //ATENTION: the order must be Tires, Oil, Brakes. This is the position in design
        vehicleStatus.add(tires);
        vehicleStatus.add(oil);
        vehicleStatus.add(brakes);
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

    private List<ServiceRecord> getLastServiceRecord(List<ServiceRecord> records) {
        return records.subList(0, Math.min(records.size(), this.MAX_SERVICE_SERVICE_RESULT));
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

    public void blockSuggestedService(Long familyVehicleId, String orderNumber, String sku) {
        FamilyVehicle familyVehicle = this.familyVehicleRepository.findOne(familyVehicleId);
        if (familyVehicle != null && familyVehicle.getFamilyId() != null && familyVehicle.getTangibleId() != null) {
            RecommendedServiceBlocked recommendedServiceBlocked
                    = new RecommendedServiceBlocked(orderNumber, familyVehicle.getFamilyId(),
                            familyVehicle.getTangibleId(), sku);
            this.recommendedServiceBlockedRepository.saveAndFlush(recommendedServiceBlocked);
        }
    }

}
