package com.searshc.mygarage.dtos.carprofile;

import com.searshc.mygarage.dtos.carprofile.component.VehicleComponentStatusDTO;
import com.searshc.mygarage.entities.record.RecommendedService;
import com.searshc.mygarage.entities.record.ServiceCenter;
import com.searshc.mygarage.entities.record.ServiceRecord;
import java.util.List;

/**
 *
 * @author rammel.maestre
 */
public class CarProfileDTO {

    private VehicleDTO vehicle;
    private List<VehicleComponentStatusDTO> vehicleStatus;
    private ServiceCenter serviceCenter;
    private RecallsInformationDTO recallsInformation;
    private RecommendedService recommendedService;
    private List<ServiceRecord> lastServiceHistory;

    public CarProfileDTO() {
    }

    public CarProfileDTO(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return the vehicle
     */
    public VehicleDTO getVehicle() {
        return vehicle;
    }

    /**
     * @param vehicle the vehicle to set
     */
    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return the vehicleStatus
     */
    public List<VehicleComponentStatusDTO> getVehicleStatus() {
        return vehicleStatus;
    }

    /**
     * @param vehicleStatus the vehicleStatus to set
     */
    public void setVehicleStatus(List<VehicleComponentStatusDTO> vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    /**
     * @return the serviceCenter
     */
    public ServiceCenter getServiceCenter() {
        return serviceCenter;
    }

    /**
     * @param serviceCenter the serviceCenter to set
     */
    public void setServiceCenter(ServiceCenter serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    /**
     * @return the recallsInformation
     */
    public RecallsInformationDTO getRecallsInformation() {
        return recallsInformation;
    }

    /**
     * @param recallsInformation the recallsInformation to set
     */
    public void setRecallsInformation(RecallsInformationDTO recallsInformation) {
        this.recallsInformation = recallsInformation;
    }

    /**
     * @return the recommendedService
     */
    public RecommendedService getRecommendedService() {
        return recommendedService;
    }

    /**
     * @param recommendedService the recommendedService to set
     */
    public void setRecommendedService(RecommendedService recommendedService) {
        this.recommendedService = recommendedService;
    }

    /**
     * @return the lastServiceHistory
     */
    public List<ServiceRecord> getLastServiceHistory() {
        return lastServiceHistory;
    }

    /**
     * @param lastServiceHistory the lastServiceHistory to set
     */
    public void setLastServiceHistory(List<ServiceRecord> lastServiceHistory) {
        this.lastServiceHistory = lastServiceHistory;
    }

}
