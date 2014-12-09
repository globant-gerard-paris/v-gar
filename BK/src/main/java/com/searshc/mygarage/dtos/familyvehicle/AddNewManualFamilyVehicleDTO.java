package com.searshc.mygarage.dtos.familyvehicle;

public class AddNewManualFamilyVehicleDTO {
	
	private Long userId;
	private Long vehicleId;
	private String make;
	private String model;
	private int year;
	private double mileage;
	private String name;
	
	public AddNewManualFamilyVehicleDTO() {
	}

	/**
	 * @param userId
	 * @param vehicleId
	 * @param make
	 * @param model
	 * @param year
	 * @param color
	 * @param mileage
	 * @param name
	 */
	public AddNewManualFamilyVehicleDTO(Long userId, Long vehicleId,
			String make, String model, int year, double mileage,
			String name) {
		super();
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.name = name;
	}



	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public double getMileage() {
		return mileage;
	}

	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(double mileage) {
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
