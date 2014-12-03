package com.searshc.mygarage.apis.ncdb.response.vintovehicle;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "VehiclesResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehiclesListByLicensePlateOrVinNumberResponse {

	@XmlElement(name = "VehicleCount")
	private int vehicleCount;
	
	@XmlElement(name = "Vehicle")
	@XmlElementWrapper(name = "Vehicles")
	private List<VehicleByLicensePlateOrVinNumberResponse> vehicles = new ArrayList<VehicleByLicensePlateOrVinNumberResponse>();
	
	public VehiclesListByLicensePlateOrVinNumberResponse() {
	}

	/**
	 * @param vehicleCount
	 * @param vehicles
	 */
	public VehiclesListByLicensePlateOrVinNumberResponse(int vehicleCount,
			List<VehicleByLicensePlateOrVinNumberResponse> vehicles) {
		super();
		this.vehicleCount = vehicleCount;
		this.vehicles = vehicles;
	}

	/**
	 * @return the vehicleCount
	 */
	public int getVehicleCount() {
		return vehicleCount;
	}

	/**
	 * @param vehicleCount the vehicleCount to set
	 */
	public void setVehicleCount(int vehicleCount) {
		this.vehicleCount = vehicleCount;
	}

	/**
	 * @return the vehicles
	 */
	public List<VehicleByLicensePlateOrVinNumberResponse> getVehicles() {
		return vehicles;
	}

	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles(List<VehicleByLicensePlateOrVinNumberResponse> vehicles) {
		this.vehicles = vehicles;
	}

}
