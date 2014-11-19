package com.searshc.mygarage.services.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.searshc.mygarage.entities.UserVehicle;

/**
 * The {@link UserVehicleStatus} is the information of the {@code Vehicle}s of the {@code userId}.
 * 
 * @author Jero
 *
 */
public class UserVehicleStatus {

	private List<UserVehicle> ncdbCars;
	private List<UserVehicle> linkedCars;
	private List<UserVehicle> manualCars;
	private Long userId;

	public UserVehicleStatus() {
		ncdbCars = new ArrayList<UserVehicle>();
		linkedCars = new ArrayList<UserVehicle>();
		manualCars = new ArrayList<UserVehicle>();
	}

	public UserVehicleStatus(List<UserVehicle> ncdbCars, List<UserVehicle> linkedCars,
			List<UserVehicle> manualCars, Long userId) {
		this.ncdbCars = ncdbCars;
		this.linkedCars = linkedCars;
		this.manualCars = manualCars;
		this.userId = userId;
	}

	public List<UserVehicle> getNcdbCars() {
		return ncdbCars;
	}

	public void setNcdbCars(List<UserVehicle> ncdbCars) {
		this.ncdbCars = ncdbCars;
	}

	public List<UserVehicle> getLinkedCars() {
		return linkedCars;
	}

	public void setLinkedCars(List<UserVehicle> linkedCars) {
		this.linkedCars = linkedCars;
	}

	public List<UserVehicle> getManualCars() {
		return manualCars;
	}

	public void setManualCars(List<UserVehicle> manualCars) {
		this.manualCars = manualCars;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
