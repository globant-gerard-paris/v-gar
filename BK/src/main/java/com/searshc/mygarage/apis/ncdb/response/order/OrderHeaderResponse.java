package com.searshc.mygarage.apis.ncdb.response.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "order_Header")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderHeaderResponse {

    @XmlElement(name = "ord_no")
    private String orderNumber;

    @XmlElement(name = "trs_dt")
    private String transactionDate;

    @XmlElement(name = "trs_lcl_tm")
    private String transactionLocalTime;

    @XmlElement(name = "odm_am")
    private Double odometerNumber;

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the transactionDate
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the transactionLocalTime
     */
    public String getTransactionLocalTime() {
        return transactionLocalTime;
    }

    /**
     * @param transactionLocalTime the transactionLocalTime to set
     */
    public void setTransactionLocalTime(String transactionLocalTime) {
        this.transactionLocalTime = transactionLocalTime;
    }

    /**
     * @return the odometerNumber
     */
    public Double getOdometerNumber() {
        return odometerNumber;
    }

    /**
     * @param odometerNumber the odometerNumber to set
     */
    public void setOdometerNumber(Double odometerNumber) {
        this.odometerNumber = odometerNumber;
    }

}
