package com.searshc.mygarage.dtos;

import java.io.Serializable;

import org.dozer.Mapping;

import com.fasterxml.jackson.annotation.JsonProperty;


public class VehicleConfirmationDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5908698017918896106L;
	@Mapping(value = "id")
	private Long vehicleId;
	private Long familyId;
	private Long tangibleId;
	private String make;
	private String model;
	private int year;
	@JsonProperty("isConfirmed")
	private boolean isConfirmed;
	
	
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
			boolean isConfirmed) {
		super();
		this.vehicleId = vehicleId;
		this.familyId = familyId;
		this.tangibleId = tangibleId;
		this.make = make;
		this.model = model;
		this.year = year;
		this.isConfirmed = isConfirmed;
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
	 * @return the isConfirmed
	 */
	public boolean isConfirmed() {
	
		return isConfirmed;
	}


	
	/**
	 * @param isConfirmed the isConfirmed to set
	 */
	public void setConfirmed(boolean isConfirmed) {
	
		this.isConfirmed = isConfirmed;
	}
	
	
}