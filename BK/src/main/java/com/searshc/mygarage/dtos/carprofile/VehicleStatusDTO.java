package com.searshc.mygarage.dtos.carprofile;

/**
 *
 * @author rammel.maestre
 */
public class VehicleStatusDTO {

    private String tireRotation;
    private String oilChange;
    private String brakesInspection;

    /**
     * @return the tireRotation
     */
    public String getTireRotation() {
        return tireRotation;
    }

    /**
     * @param tireRotation the tireRotation to set
     */
    public void setTireRotation(String tireRotation) {
        this.tireRotation = tireRotation;
    }

    /**
     * @return the oilChange
     */
    public String getOilChange() {
        return oilChange;
    }

    /**
     * @param oilChange the oilChange to set
     */
    public void setOilChange(String oilChange) {
        this.oilChange = oilChange;
    }

    /**
     * @return the brakesInspection
     */
    public String getBrakesInspection() {
        return brakesInspection;
    }

    /**
     * @param brakesInspection the brakesInspection to set
     */
    public void setBrakesInspection(String brakesInspection) {
        this.brakesInspection = brakesInspection;
    }

}
