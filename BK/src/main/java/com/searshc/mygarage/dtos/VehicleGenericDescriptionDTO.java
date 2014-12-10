package com.searshc.mygarage.dtos;

public class VehicleGenericDescriptionDTO {

	private long familyVehicleId;
	
	private long vehicleId;
	
	private String make;
	
	private String model;
	
	private int year;
	
	private long familyId;
	
	private long tangibleId;
	
	private String color;
	
	private String name;
	
	private int mileage;
	
	private String engine;
	
	private String vinNumber;
	
	private String licensePlate;
	
	private String licensePlatestate;
	
	public VehicleGenericDescriptionDTO() {
	}

	/**
	 * @param familyVehicleId
	 * @param vehicleId
	 * @param make
	 * @param model
	 * @param year
	 * @param familyId
	 * @param tangibleId
	 * @param color
	 * @param name
	 * @param mileage
	 * @param engine
	 * @param vinNumber
	 * @param licensePlate
	 * @param licensePlatestate
	 */
	public VehicleGenericDescriptionDTO(long familyVehicleId, long vehicleId,
			String make, String model, int year, long familyId,
			long tangibleId, String color, String name, int mileage,
			String engine, String vinNumber, String licensePlate,
			String licensePlatestate) {
		super();
		this.familyVehicleId = familyVehicleId;
		this.vehicleId = vehicleId;
		this.make = make;
		this.model = model;
		this.year = year;
		this.familyId = familyId;
		this.tangibleId = tangibleId;
		this.color = color;
		this.name = name;
		this.mileage = mileage;
		this.engine = engine;
		this.vinNumber = vinNumber;
		this.licensePlate = licensePlate;
		this.licensePlatestate = licensePlatestate;
	}

	/**
	 * @return the familyVehicleId
	 */
	public long getFamilyVehicleId() {
		return familyVehicleId;
	}

	/**
	 * @param familyVehicleId the familyVehicleId to set
	 */
	public void setFamilyVehicleId(long familyVehicleId) {
		this.familyVehicleId = familyVehicleId;
	}

	/**
	 * @return the vehicleId
	 */
	public long getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(long vehicleId) {
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
	 * @return the familyId
	 */
	public long getFamilyId() {
		return familyId;
	}

	/**
	 * @param familyId the familyId to set
	 */
	public void setFamilyId(long familyId) {
		this.familyId = familyId;
	}

	/**
	 * @return the tangibleId
	 */
	public long getTangibleId() {
		return tangibleId;
	}

	/**
	 * @param tangibleId the tangibleId to set
	 */
	public void setTangibleId(long tangibleId) {
		this.tangibleId = tangibleId;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
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
	 * @return the vinNumber
	 */
	public String getVinNumber() {
		return vinNumber;
	}

	/**
	 * @param vinNumber the vinNumber to set
	 */
	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	/**
	 * @return the licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * @param licensePlate the licensePlate to set
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
	 * @return the licensePlatestate
	 */
	public String getLicensePlatestate() {
		return licensePlatestate;
	}

	/**
	 * @param licensePlatestate the licensePlatestate to set
	 */
	public void setLicensePlatestate(String licensePlatestate) {
		this.licensePlatestate = licensePlatestate;
	}

}
