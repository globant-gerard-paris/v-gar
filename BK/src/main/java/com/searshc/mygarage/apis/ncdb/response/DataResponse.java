package com.searshc.mygarage.apis.ncdb.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Data")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataResponse {

    @XmlElement(name = "vehicle_Typs")
    @XmlElementWrapper(name = "FamilyVehicle")
    private List<VehicleResponse> vehicles = new ArrayList<VehicleResponse>();

    @XmlAttribute(name = "AppResponse")
    private String appResponseCode;

    @XmlAttribute(name = "AppResponseDesc")
    private String appResponseDescription;

    /**
     * @return the vehicles
     */
    public List<VehicleResponse> getVehicles() {
        return vehicles;
    }

    /**
     * @param vehicles the vehicles to set
     */
    public void setVehicles(List<VehicleResponse> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * @return the appResponseCode
     */
    public String getAppResponseCode() {
        return appResponseCode;
    }

    /**
     * @param appResponseCode the appResponseCode to set
     */
    public void setAppResponseCode(String appResponseCode) {
        this.appResponseCode = appResponseCode;
    }

    /**
     * @return the appResponseDescription
     */
    public String getAppResponseDescription() {
        return appResponseDescription;
    }

    /**
     * @param appResponseDescription the appResponseDescription to set
     */
    public void setAppResponseDescription(String appResponseDescription) {
        this.appResponseDescription = appResponseDescription;
    }

}
