package com.searshc.mygarage.apis.ncdb.response.vehicle;

import com.searshc.mygarage.apis.ncdb.response.EsbMsg;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ESBMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleRetrievalResponse extends EsbMsg {

    @XmlElement(name = "Data")
    private VehicleDataResponse data;

    /**
     * @return the vehicles
     */
    public List<VehicleResponse> getVehicles() {
        return data != null ? this.data.getVehicles() : new ArrayList<VehicleResponse>();
    }

    /**
     * @return the data
     */
    public VehicleDataResponse getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(VehicleDataResponse data) {
        this.data = data;
    }

}
