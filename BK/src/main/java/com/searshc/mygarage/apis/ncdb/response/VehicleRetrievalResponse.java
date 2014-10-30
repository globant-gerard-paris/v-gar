package com.searshc.mygarage.apis.ncdb.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ESBMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleRetrievalResponse {

    @XmlElement(name = "Data")
    private DataResponse data;

    @XmlElement(name = "MDSHeader")
    private MdsHeader mdsHeader;

    /**
     * @return the data
     */
    public DataResponse getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(DataResponse data) {
        this.data = data;
    }

    /**
     * @return the mdsHeader
     */
    public MdsHeader getMdsHeader() {
        return mdsHeader;
    }

    /**
     * @param mdsHeader the msdHeader to set
     */
    public void setMsdHeader(MdsHeader mdsHeader) {
        this.mdsHeader = mdsHeader;
    }

}
