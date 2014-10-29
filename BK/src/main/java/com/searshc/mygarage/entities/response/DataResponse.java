package com.searshc.mygarage.entities.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Data")
public class DataResponse {

    private List<VehicleResponse> vehicles = new ArrayList<VehicleResponse>();

    private String familyVehicle;

    @XmlElement(name = "vehicle_Typs") 
    @XmlElementWrapper(name = "FamilyVehicle") 
    public List<VehicleResponse> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleResponse> vehicles) {
        this.vehicles = vehicles;
    }

    @XmlElement(name = "FamilyVehicle")
    public String getFamilyVehicle() {
        return familyVehicle;
    }

    /**
     * @param familyVehicle the familyVehicle to set
     */
    public void setFamilyVehicle(String familyVehicle) {
        this.familyVehicle = familyVehicle;
    }

}
