package com.searshc.mygarage.dtos.carprofile.component;

public class VehicleComponentStatusDTO {
	
	private String componentName;
	private String statusName;
	private String statusDescription;
	
	public VehicleComponentStatusDTO() {
	}

	/**
	 * @param componentName
	 * @param statusName
	 * @param statusDescription
	 */
	public VehicleComponentStatusDTO(String componentName, String statusName,
			String statusDescription) {
		super();
		this.componentName = componentName;
		this.statusName = statusName;
		this.statusDescription = statusDescription;
	}

	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * @param statusDescription the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

}
