package com.searshc.mygarage.dtos;

import java.io.Serializable;

import org.dozer.Mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleConfirmationDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5908698017918896106L;
    @Mapping(value = "id")
    //TODO: vehicleId should be changed to familyVehicleId
    private Long vehicleId;
    private Long familyId;
    private Long tangibleId;
    private int mileage;
	@Mapping("vehicle.make")
    private String make;
    @Mapping("vehicle.model")
    private String model;
    @Mapping("vehicle.year")
    private int year;
    @Mapping("vehicle.engine")
    private String engine;
    @JsonProperty("isConfirmed")
    private boolean isConfirmed;
    private Status status;

    public VehicleConfirmationDTO() {
    }

    /**
     * @param vehicleId
     * @param familyId
     * @param tangibleId
     * @param make
     * @param model
     * @param year
     * @param isConfirmed
     */
    public VehicleConfirmationDTO(Long vehicleId, Long familyId, Long tangibleId, String make, String model, int year,
            String engine, boolean isConfirmed, Status status) {
        super();
        this.vehicleId = vehicleId;
        this.familyId = familyId;
        this.tangibleId = tangibleId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.isConfirmed = isConfirmed;
        this.status = status;
    }

    /**
     * @return the vehicleId
     */
    public Long getVehicleId() {

        return vehicleId;
    }

    /**
     * @param vehicleId the vehicleId to set
     */
    public void setVehicleId(Long vehicleId) {

        this.vehicleId = vehicleId;
    }

    /**
     * @return the familyId
     */
    public Long getFamilyId() {

        return familyId;
    }

    /**
     * @param familyId the familyId to set
     */
    public void setFamilyId(Long familyId) {

        this.familyId = familyId;
    }

    /**
     * @return the tangibleId
     */
    public Long getTangibleId() {

        return tangibleId;
    }

    /**
     * @param tangibleId the tangibleId to set
     */
    public void setTangibleId(Long tangibleId) {

        this.tangibleId = tangibleId;
    }

    /**
     * @return the make
     */
    public String getMake() {

        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {

        this.make = make;
    }

    /**
     * @return the model
     */
    public String getModel() {

        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {

        this.model = model;
    }

    /**
     * @return the year
     */
    public int getYear() {

        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {

        this.year = year;
    }

    /**
	 * @return the engine
	 */
	public String getEngine() {
		return engine;
	}

	/**
	 * @param engine the engine to set
	 */
	public void setEngine(String engine) {
		this.engine = engine;
	}

	/**
     * @return the isConfirmed
     */
    @JsonIgnore
    public boolean isConfirmed() {

        return isConfirmed;
    }

    /**
     * @param isConfirmed the isConfirmed to set
     */
    public void setConfirmed(boolean isConfirmed) {

        this.isConfirmed = isConfirmed;
    }

    /**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

    public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public enum Status {NCDB, LINKED, MANUAL};

}
