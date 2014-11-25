/**
 * 
 */
package com.searshc.mygarage.dtos.home;

import java.io.Serializable;
import java.util.Set;

import com.searshc.mygarage.dtos.VehicleConfirmationDTO;

/**
 * @author Jero
 *
 */
public class HomeDto implements Serializable {

	private Set<VehicleConfirmationDTO> vehicleInformation;
	private Long userId;

	public HomeDto() {
	}

	public HomeDto(Set<VehicleConfirmationDTO> vehicleInformation, Long userId) {
		this.vehicleInformation = vehicleInformation;
		this.userId = userId;
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

}
