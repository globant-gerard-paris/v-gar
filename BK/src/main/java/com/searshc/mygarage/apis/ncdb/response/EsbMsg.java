package com.searshc.mygarage.apis.ncdb.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EsbMsg {

    @XmlElement(name = "MDSHeader")
    protected MdsHeader mdsHeader;

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
