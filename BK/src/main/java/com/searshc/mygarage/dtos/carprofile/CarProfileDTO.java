package com.searshc.mygarage.dtos.carprofile;

import com.searshc.mygarage.entities.record.ServiceCenter;
import com.searshc.mygarage.entities.record.ServiceRecord;
import java.util.List;

/**
 *
 * @author rammel.maestre
 */
public class CarProfileDTO {

    private VehicleDTO vehicle;
    private VehicleStatusDTO vehicleStatus;
    private ServiceCenter serviceCenter;
    private RecallsInformationDTO recallsInformation;
    private List<ServiceRecord> lastServiceHitory;

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
    public VehicleStatusDTO getVehicleStatus() {
        return vehicleStatus;
    }

    /**
     * @param vehicleStatus the vehicleStatus to set
     */
    public void setVehicleStatus(VehicleStatusDTO vehicleStatus) {
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
     * @return the lastServiceHitory
     */
    public List<ServiceRecord> getLastServiceHitory() {
        return lastServiceHitory;
    }

    /**
     * @param lastServiceHitory the lastServiceHitory to set
     */
    public void setLastServiceHitory(List<ServiceRecord> lastServiceHitory) {
        this.lastServiceHitory = lastServiceHitory;
    }

}
