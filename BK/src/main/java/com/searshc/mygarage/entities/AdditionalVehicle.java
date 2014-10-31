package com.searshc.mygarage.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.Validate;

@Entity
@Table(name = "additional_vehicle")
public class AdditionalVehicle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -650535603949006577L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "INT UNSIGNED")
	private Integer id;
	
	//TODO: change the String to another data type
	@Column(name = "make")
	private String make;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "year")
	private int year;
	
	//TODO: change the String to another data type
	@Column(name = "color")
	private String color;
	
	@Column(name = "mileage")
	private int mileage;
	
	public AdditionalVehicle() {
		this.id = 0;
		this.make = "";
		this.model = "";
		this.year = 0;
		this.color = "";
		this.mileage = 0;
	}
	
	

	/**
	 * @param id
	 * @param make
	 * @param model
	 * @param year
	 * @param color
	 * @param mileage
	 */
	public AdditionalVehicle(Integer id, String make, String model, int year, String color,
			int mileage) {
		Validate.isTrue(id!= null && id >= 0, "The id cannot be lower than 0");
		Validate.notNull(make, "The Make cannot be null");
		Validate.notNull(model, "The Model cannot be null");
		Validate.isTrue(year > 0, "The Year cannot be lower than 0");
		Validate.notNull(color, "The Color cannot be null");
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.mileage = mileage;
	}



	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	
}
