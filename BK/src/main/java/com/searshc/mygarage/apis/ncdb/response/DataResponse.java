package com.searshc.mygarage.apis.ncdb.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Data")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class DataResponse {

    @XmlAttribute(name = "AppResponse")
    protected String appResponseCode;

    @XmlAttribute(name = "AppResponseDesc")
    protected String appResponseDescription;

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
