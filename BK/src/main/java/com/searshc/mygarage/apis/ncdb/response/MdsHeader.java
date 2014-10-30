package com.searshc.mygarage.apis.ncdb.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "MDSHeader")
@XmlAccessorType(XmlAccessType.FIELD)
public class MdsHeader {

    @XmlElement(name = "HeaderType")
    private String headerType = "HDR-MDSREQ";

    @XmlElement(name = "VersionNumber")
    private String versionNumber = "001";

    @XmlElement(name = "ServiceName")
    private String serviceName;

    @XmlElement(name = "RequestorUserId")
    private String requestorUserId;

    @XmlElement(name = "MessageOriginationTime")
    private String messageOriginationTime;

    @XmlElement(name = "SequenceNumber")
    private String sequenceNumber;

    @XmlElement(name = "ResponseCode")
    private String responseCode;

    @XmlElement(name = "ResponseDescription")
    private String responseDescription;

    public MdsHeader() {
    }

    public MdsHeader(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the headerType
     */
    public String getHeaderType() {
        return headerType;
    }

    /**
     * @param headerType the headerType to set
     */
    public void setHeaderType(String headerType) {
        this.headerType = headerType;
    }

    /**
     * @return the versionNumber
     */
    public String getVersionNumber() {
        return versionNumber;
    }

    /**
     * @param versionNumber the versionNumber to set
     */
    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the requestorUserId
     */
    public String getRequestorUserId() {
        return requestorUserId;
    }

    /**
     * @param requestorUserId the requestorUserId to set
     */
    public void setRequestorUserId(String requestorUserId) {
        this.requestorUserId = requestorUserId;
    }

    /**
     * @return the messageOriginationTime
     */
    public String getMessageOriginationTime() {
        return messageOriginationTime;
    }

    /**
     * @param messageOriginationTime the messageOriginationTime to set
     */
    public void setMessageOriginationTime(String messageOriginationTime) {
        this.messageOriginationTime = messageOriginationTime;
    }

    /**
     * @return the sequenceNumber
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @param sequenceNumber the sequenceNumber to set
     */
    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the responseDescription
     */
    public String getResponseDescription() {
        return responseDescription;
    }

    /**
     * @param responseDescription the responseDescription to set
     */
    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

}
