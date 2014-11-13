package com.searshc.mygarage.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


@Entity
@Table(name = "vehicle")
public class Vehicle extends AbstractEntity implements Serializable{

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = -650535603949006577L;

	/**
	 * The NCDB Family Id. Optional.
	 * A value indicates that the vehicle belongs to a family instead an individual user.
	 */
	@Column(name = "familyid", nullable = true, unique = true)
	private Long familyId;
	
	/**
	 * The Tangible Id. Optional. It is the id used in NCDB to identify uniquely a vehicle.
	 * A value indicates that the vehicle is registered in NCDB, otherwise is a local vehicle.
	 */
	@Column(name = "tangibleid", nullable = true)
	private Long tangibleId;
	
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
	
	
	public Vehicle() {
		this.familyId = null;
		this.tangibleId = null;
		this.make = "";
		this.model = "";
		this.year = 0;
		this.color = "";
		this.mileage = 0;
	}
	
	
	/**
	 * @param familyId: Optional
	 * @param tangibleId: Optional
	 * @param make: Required
	 * @param model: Required
	 * @param year: Required
	 * @param color: Required
	 * @param mileage: Optional
	 */
	public Vehicle(final Long familyId, final Long tangibleId, final String make,
			final String model, final int year, final String color, final int mileage) {
		this.familyId = familyId; //No validation required
		this.tangibleId = tangibleId; //No validation required
		this.make = Validate.notNull(make, "The Make cannot be null");
		this.model = Validate.notNull(model, "The Model cannot be null");
		Validate.isTrue(year > 0, "The Year cannot be lower than 0");
		this.year = year;
		this.color = color;
		this.mileage = mileage;
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
		this.make = Validate.notNull(make, "The Make cannot be null");
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
		this.model = Validate.notNull(model, "The Model cannot be null");
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
		Validate.isTrue(year > 0, "The Year cannot be lower than 0");
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
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.familyId)
			.append(this.tangibleId)
			.append(this.make)
			.append(this.model)
			.append(this.year)
			.append(this.color).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle rhs = (Vehicle) obj;
		return new EqualsBuilder()
			.append(this.familyId, rhs.familyId)
			.append(this.tangibleId, rhs.tangibleId)
			.append(this.make, rhs.make)
			.append(this.model, rhs.model)
			.append(this.year, rhs.year)
			//.append(this.color, rhs.color).isEquals();
			.isEquals();
	}
}
