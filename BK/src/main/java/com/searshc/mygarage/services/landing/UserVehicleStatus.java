package com.searshc.mygarage.services.landing;

import java.util.List;

/**
 * @author Jero
 *
 */
public class UserVehicleStatus {

	private List<String> ncdbCars;
	private List<String> linkedCars;
	private List<String> manualCars;
	private String userId;

	public UserVehicleStatus() {
	}

	public UserVehicleStatus(List<String> ncdbCars, List<String> linkedCars, List<String> manualCars,
			String userId) {
		this.ncdbCars = ncdbCars;
		this.linkedCars = linkedCars;
		this.manualCars = manualCars;
		this.userId = userId;
	}

	public List<String> getNcdbCars() {
		return ncdbCars;
	}

	public void setNcdbCars(List<String> ncdbCars) {
		this.ncdbCars = ncdbCars;
	}

	public List<String> getLinkedCars() {
		return linkedCars;
	}

	public void setLinkedCars(List<String> linkedCars) {
		this.linkedCars = linkedCars;
	}

	public List<String> getManualCars() {
		return manualCars;
	}

	public void setManualCars(List<String> manualCars) {
		this.manualCars = manualCars;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
