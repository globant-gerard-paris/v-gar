package com.searshc.mygarage.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


@Entity
public class ConfirmedVehicle extends AbstractEntity implements Serializable{
	
	/**
	 * The Serial Version UID
	 */
	private static final long serialVersionUID = -2472653437246767303L;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_information_id"))
	private User user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_vehicle_id"))
	private UserVehicle userVehicle;
	
	public ConfirmedVehicle() {
		this.user = null;
		this.userVehicle = null;
	}


	/**
	 * @param user
	 * @param userVehicle
	 * @param isConfirmed
	 */
	public ConfirmedVehicle(final User user, final UserVehicle userVehicle) {
		super();
		this.user = Validate.notNull(user, "The User cannot be null");
		this.userVehicle = Validate.notNull(userVehicle, "The Vehicle cannot be null");
	}


	
	/**
	 * @return the userInformation
	 */
	public User getUserInformation() {
		return user;
	}


	
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	
	/**
	 * @return the vehicle
	 */
	public UserVehicle getVehicle() {
		return userVehicle;
	}


	
	/**
	 * @param userVehicle the vehicle to set
	 */
	public void setVehicle(UserVehicle userVehicle) {
		this.userVehicle = userVehicle;
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.user)
			.append(this.userVehicle).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfirmedVehicle rhs = (ConfirmedVehicle) obj;
		return new EqualsBuilder()
			.append(this.user, rhs.user)
			.append(this.userVehicle, rhs.userVehicle).isEquals();
	}
	
}
