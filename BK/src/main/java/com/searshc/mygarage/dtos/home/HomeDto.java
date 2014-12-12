package com.searshc.mygarage.dtos.home;

import java.io.Serializable;
import java.util.Set;

import com.searshc.mygarage.dtos.VehicleConfirmationDTO;

/**
 * @author Jero
 *
 */
public class HomeDto implements Serializable {

	private static final long serialVersionUID = 5730843378315981643L;
	private Set<VehicleConfirmationDTO> vehicleInformation;
	private Long userId;
	private String userName;
	private String sywMemberNumber;

	public HomeDto() {
	}

	public HomeDto(final Set<VehicleConfirmationDTO> vehicleInformation, final Long userId, final String userName, final String sywMemberNumber) {
		this.vehicleInformation = vehicleInformation;
		this.userId = userId;
		this.userName = userName;
		this.sywMemberNumber = sywMemberNumber;
	}

	public Set<VehicleConfirmationDTO> getVehicleInformation() {
		return vehicleInformation;
	}

	public void setVehicleInformation(Set<VehicleConfirmationDTO> vehicleInformation) {
		this.vehicleInformation = vehicleInformation;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSywMemberNumber() {
		return sywMemberNumber;
	}

	public void setSywMemberNumber(String sywMemberNumber) {
		this.sywMemberNumber = sywMemberNumber;
	}
	
	
	

}
