package com.searshc.mygarage.apis.ncdb.response.vehicle;

import com.searshc.mygarage.apis.ncdb.response.DataResponse;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleDataResponse extends DataResponse {

    @XmlElement(name = "vehicle_Typs")
    @XmlElementWrapper(name = "FamilyVehicle")
    private List<VehicleResponse> vehicles = new ArrayList<VehicleResponse>();

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

}
