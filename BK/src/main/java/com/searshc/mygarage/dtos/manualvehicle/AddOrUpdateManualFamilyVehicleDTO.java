package com.searshc.mygarage.dtos.manualvehicle;

public class AddOrUpdateManualFamilyVehicleDTO {

	private Long familyVehicleId;
	private Long vehicleId;
	private String make;
	private String model;
	private int year;
	private int mileage;
	private String name;
	
	public AddOrUpdateManualFamilyVehicleDTO() {
	}

	/**
	 * @param userId
	 * @param familyVehicleId
	 * @param vehicleId
	 * @param make
	 * @param model
	 * @param year
	 * @param mileage
	 * @param name
	 */
	public AddOrUpdateManualFamilyVehicleDTO(Long userId, Long familyVehicleId,
			Long vehicleId, String make, String model, int year, int mileage,
			String name) {
		super();
		this.familyVehicleId = familyVehicleId;
		this.vehicleId = vehicleId;
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.name = name;
	}

	/**
	 * @return the familyVehicleId
	 */
	public Long getFamilyVehicleId() {
		return familyVehicleId;
	}

	/**
	 * @param familyVehicleId the familyVehicleId to set
	 */
	public void setFamilyVehicleId(Long familyVehicleId) {
		this.familyVehicleId = familyVehicleId;
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
	 * @return the mileage
	 */
	public int getMileage() {
		return mileage;
	}

	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
