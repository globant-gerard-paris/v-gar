package com.searshc.mygarage.entities.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ESBMsg")
public class VehicleRetrievalResponse {

    private DataResponse data;

    @XmlElement(name = "Data")
    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }

}
