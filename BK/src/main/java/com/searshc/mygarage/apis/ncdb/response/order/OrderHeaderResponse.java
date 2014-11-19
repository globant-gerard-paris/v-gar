package com.searshc.mygarage.apis.ncdb.response.order;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "order_Header")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderHeaderResponse {

    @XmlElement(name = "ord_no")
    private String orderNumber;

    @XmlElement(name = "fam_id_no")
    private Long familyIdNumber;

    @XmlElement(name = "cus_id_no")
    private String customerIdNumber;

    @XmlElement(name = "tng_id_no")
    private Long tangibleIdNumber;

    @XmlElement(name = "str_no")
    private Integer storeNumber;

    @XmlElement(name = "reg_no")
    private Integer registerNumber;

    @XmlElement(name = "trs_no")
    private Integer transactionNumber;

    @XmlElement(name = "trs_dt")
    private Date transactionDate;

    @XmlElement(name = "trs_lcl_tm")
    private Date transactionLocalTime;

    @XmlElement(name = "ord_ref_no")
    private String orderReferenceNumber;

    @XmlElement(name = "odm_am")
    private Double odometerAmount;

    @XmlElement(name = "ord_ori_cd")
    private String orderOriginationCode;

    @XmlElement(name = "ord_tot_am")
    private Double orderTotalAmount;

    @XmlElement(name = "rin_asc_id_no")
    private String ringingAssociateId;

    @XmlElement(name = "lyl_id_no")
    private String loyaltyIdNumber;

    @XmlElement(name = "ord_cmt_tx")
    private String orderCommentText;

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
     * @return the familyIdNumber
     */
    public Long getFamilyIdNumber() {
        return familyIdNumber;
    }

    /**
     * @param familyIdNumber the familyIdNumber to set
     */
    public void setFamilyIdNumber(Long familyIdNumber) {
        this.familyIdNumber = familyIdNumber;
    }

    /**
     * @return the customerIdNumber
     */
    public String getCustomerIdNumber() {
        return customerIdNumber;
    }

    /**
     * @param customerIdNumber the customerIdNumber to set
     */
    public void setCustomerIdNumber(String customerIdNumber) {
        this.customerIdNumber = customerIdNumber;
    }

    /**
     * @return the tangibleIdNumber
     */
    public Long getTangibleIdNumber() {
        return tangibleIdNumber;
    }

    /**
     * @param tangibleIdNumber the tangibleIdNumber to set
     */
    public void setTangibleIdNumber(Long tangibleIdNumber) {
        this.tangibleIdNumber = tangibleIdNumber;
    }

    /**
     * @return the storeNumber
     */
    public Integer getStoreNumber() {
        return storeNumber;
    }

    /**
     * @param storeNumber the storeNumber to set
     */
    public void setStoreNumber(Integer storeNumber) {
        this.storeNumber = storeNumber;
    }

    /**
     * @return the registerNumber
     */
    public Integer getRegisterNumber() {
        return registerNumber;
    }

    /**
     * @param registerNumber the registerNumber to set
     */
    public void setRegisterNumber(Integer registerNumber) {
        this.registerNumber = registerNumber;
    }

    /**
     * @return the transactionNumber
     */
    public Integer getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * @param transactionNumber the transactionNumber to set
     */
    public void setTransactionNumber(Integer transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    /**
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the transactionLocalTime
     */
    public Date getTransactionLocalTime() {
        return transactionLocalTime;
    }

    /**
     * @param transactionLocalTime the transactionLocalTime to set
     */
    public void setTransactionLocalTime(Date transactionLocalTime) {
        this.transactionLocalTime = transactionLocalTime;
    }

    /**
     * @return the orderReferenceNumber
     */
    public String getOrderReferenceNumber() {
        return orderReferenceNumber;
    }

    /**
     * @param orderReferenceNumber the orderReferenceNumber to set
     */
    public void setOrderReferenceNumber(String orderReferenceNumber) {
        this.orderReferenceNumber = orderReferenceNumber;
    }

    /**
     * @return the odometerAmount
     */
    public Double getOdometerAmount() {
        return odometerAmount;
    }

    /**
     * @param odometerAmount the odometerAmount to set
     */
    public void setOdometerAmount(Double odometerAmount) {
        this.odometerAmount = odometerAmount;
    }

    /**
     * @return the orderOriginationCode
     */
    public String getOrderOriginationCode() {
        return orderOriginationCode;
    }

    /**
     * @param orderOriginationCode the orderOriginationCode to set
     */
    public void setOrderOriginationCode(String orderOriginationCode) {
        this.orderOriginationCode = orderOriginationCode;
    }

    /**
     * @return the orderTotalAmount
     */
    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    /**
     * @param orderTotalAmount the orderTotalAmount to set
     */
    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    /**
     * @return the ringingAssociateId
     */
    public String getRingingAssociateId() {
        return ringingAssociateId;
    }

    /**
     * @param ringingAssociateId the ringingAssociateId to set
     */
    public void setRingingAssociateId(String ringingAssociateId) {
        this.ringingAssociateId = ringingAssociateId;
    }

    /**
     * @return the loyaltyIdNumber
     */
    public String getLoyaltyIdNumber() {
        return loyaltyIdNumber;
    }

    /**
     * @param loyaltyIdNumber the loyaltyIdNumber to set
     */
    public void setLoyaltyIdNumber(String loyaltyIdNumber) {
        this.loyaltyIdNumber = loyaltyIdNumber;
    }

    /**
     * @return the orderCommentText
     */
    public String getOrderCommentText() {
        return orderCommentText;
    }

    /**
     * @param orderCommentText the orderCommentText to set
     */
    public void setOrderCommentText(String orderCommentText) {
        this.orderCommentText = orderCommentText;
    }

}
