package com.searshc.mygarage.dtos.carprofile.component;

public class VehicleComponentStatusDTO {

    private String component;
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
    public VehicleComponentStatusDTO(String component, String componentName, String statusName,
            String statusDescription) {
        super();
        this.component = component;
        this.componentName = componentName;
        this.statusName = statusName;
        this.statusDescription = statusDescription;
    }

    /**
     * @return the component
     */
    public String getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(String component) {
        this.component = component;
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
