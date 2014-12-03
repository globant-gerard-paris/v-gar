package com.searshc.mygarage.apis.ncdb.response.vintovehicle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.dozer.Mapping;

@XmlType(name = "Vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleByLicensePlateOrVinNumberResponse {

	@XmlElement(name = "ID")
	private long id;
	@XmlElement(name = "Year")
	private int year;
	@XmlElement(name = "Make")
	private String make;
	@XmlElement(name = "Model")
	private String model;
	@XmlElement(name = "Engine")
	private String engine;
	@XmlElement(name = "VIN")
	@Mapping("vinNumber")
	private String vin;
	@XmlElement(name = "LicensePlate")
	private String licensePlate;
	@XmlElement(name = "LicensePlateState")
	private String licensePlateState;
	
	public VehicleByLicensePlateOrVinNumberResponse() {
	}

	/**
	 * @param id
	 * @param year
	 * @param make
	 * @param model
	 * @param engine
	 * @param vin
	 * @param licensePlate
	 * @param licensePlateState
	 */
	public VehicleByLicensePlateOrVinNumberResponse(long id, int year, String make, String model,
			String engine, String vin, String licensePlate,
			String licensePlateState) {
		super();
		this.id = id;
		this.year = year;
		this.make = make;
		this.model = model;
		this.engine = engine;
		this.vin = vin;
		this.licensePlate = licensePlate;
		this.licensePlateState = licensePlateState;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
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
	 * @return the licensePlateState
	 */
	public String getLicensePlateState() {
		return licensePlateState;
	}

	/**
	 * @param licensePlateState the licensePlateState to set
	 */
	public void setLicensePlateState(String licensePlateState) {
		this.licensePlateState = licensePlateState;
	}

}
