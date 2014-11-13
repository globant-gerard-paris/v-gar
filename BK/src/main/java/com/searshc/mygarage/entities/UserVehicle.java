package com.searshc.mygarage.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;


@Entity
public class UserVehicle extends AbstractEntity implements Serializable{
	
	/**
	 * The Serial Version UID
	 */
	private static final long serialVersionUID = -2472653437246767303L;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_information_id"))
	private UserInformation userInformation;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_vehicle_id"))
	private Vehicle vehicle;
	
	@Column(name = "isconfirmed")
	private boolean isConfirmed;
	
	
	public UserVehicle() {
		this.userInformation = null;
		this.vehicle = null;
		this.isConfirmed = false;
	}


	/**
	 * @param user
	 * @param vehicle
	 * @param isConfirmed
	 */
	public UserVehicle(final UserInformation user, final Vehicle vehicle, final boolean isConfirmed) {
		super();
		this.userInformation = Validate.notNull(user, "The User cannot be null");
		this.vehicle = Validate.notNull(vehicle, "The Vehicle cannot be null");
		this.isConfirmed = isConfirmed;
	}


	
	/**
	 * @return the userInformation
	 */
	public UserInformation getUserInformation() {
	
		return userInformation;
	}


	
	/**
	 * @param userInformation the user to set
	 */
	public void setUser(UserInformation userInformation) {
		this.userInformation = userInformation;
	}


	
	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
	
		return vehicle;
	}


	
	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
	
		this.vehicle = vehicle;
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
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.userInformation)
			.append(this.vehicle)
			.append(this.isConfirmed).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVehicle rhs = (UserVehicle) obj;
		return new EqualsBuilder()
			.append(this.userInformation, rhs.userInformation)
			.append(this.vehicle, rhs.vehicle)
			.append(this.isConfirmed, rhs.isConfirmed).isEquals();
	}
	
}
