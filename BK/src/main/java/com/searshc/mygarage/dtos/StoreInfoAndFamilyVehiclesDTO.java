package com.searshc.mygarage.dtos;

import java.util.Set;

import com.searshc.mygarage.entities.FamilyVehicle;
import com.searshc.mygarage.entities.Store;

public class StoreInfoAndFamilyVehiclesDTO {
	private Store store;
	private Set<FamilyVehicle> vehicles;
	
	public StoreInfoAndFamilyVehiclesDTO() {
	}

	/**
	 * @param store
	 * @param vehicles
	 */
	public StoreInfoAndFamilyVehiclesDTO(Store store, Set<FamilyVehicle> vehicles) {
		this.store = store;
		this.vehicles = vehicles;
	}

	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
	}

	/**
	 * @return the vehicles
	 */
	public Set<FamilyVehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles(Set<FamilyVehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
}
