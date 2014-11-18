package com.searshc.mygarage.services.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.searshc.mygarage.entities.FamilyVehicle;

/**
 * The {@link UserVehicleStatus} is the information of the {@code Vehicle}s of the {@code userId}.
 * 
 * @author Jero
 *
 */
public class UserVehicleStatus {

	private List<FamilyVehicle> ncdbCars;
	private List<FamilyVehicle> linkedCars;
	private List<FamilyVehicle> manualCars;
	private Long userId;

	public UserVehicleStatus() {
		ncdbCars = new ArrayList<FamilyVehicle>();
		linkedCars = new ArrayList<FamilyVehicle>();
		manualCars = new ArrayList<FamilyVehicle>();
	}

	public UserVehicleStatus(List<FamilyVehicle> ncdbCars, List<FamilyVehicle> linkedCars,
			List<FamilyVehicle> manualCars, Long userId) {
		this.ncdbCars = ncdbCars;
		this.linkedCars = linkedCars;
		this.manualCars = manualCars;
		this.userId = userId;
	}

	public List<FamilyVehicle> getNcdbCars() {
		return ncdbCars;
	}

	public void setNcdbCars(List<FamilyVehicle> ncdbCars) {
		this.ncdbCars = ncdbCars;
	}

	public List<FamilyVehicle> getLinkedCars() {
		return linkedCars;
	}

	public void setLinkedCars(List<FamilyVehicle> linkedCars) {
		this.linkedCars = linkedCars;
	}

	public List<FamilyVehicle> getManualCars() {
		return manualCars;
	}

	public void setManualCars(List<FamilyVehicle> manualCars) {
		this.manualCars = manualCars;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
