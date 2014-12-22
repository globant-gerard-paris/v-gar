package com.searshc.mygarage.dtos.carprofile;

import java.util.LinkedList;
import java.util.List;

import com.searshc.mygarage.dtos.carprofile.component.VehicleComponentStatusDTO;

/**
 *
 * @author rammel.maestre
 */
public class VehicleStatusDTO {

	private List<VehicleComponentStatusDTO> vehicleComponentStatus;
	
	public VehicleStatusDTO() {
	}

	/**
	 * @param vehicleComponentStatus
	 */
	public VehicleStatusDTO(
			List<VehicleComponentStatusDTO> vehicleComponentStatus) {
		super();
		this.vehicleComponentStatus = vehicleComponentStatus;
	}

	/**
	 * @return the vehicleComponentStatus
	 */
	public List<VehicleComponentStatusDTO> getVehicleComponentStatus() {
		return vehicleComponentStatus;
	}

	/**
	 * @param vehicleComponentStatus the vehicleComponentStatus to set
	 */
	public void setVehicleComponentStatus(
			List<VehicleComponentStatusDTO> vehicleComponentStatus) {
		this.vehicleComponentStatus = vehicleComponentStatus;
	}
	
	public void addVehicleComponentStatus(VehicleComponentStatusDTO vehicleComponentStatusDTO) {
		if(this.vehicleComponentStatus == null) {
			this.vehicleComponentStatus = new LinkedList<VehicleComponentStatusDTO>();
		}
		this.vehicleComponentStatus.add(vehicleComponentStatusDTO);
	}

}
