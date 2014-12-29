package com.searshc.mygarage.entities.record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.Validate;

public class Order {

    private String orderNumber;
    private Long familyIdNumber;
    private Long tangibleIdNumber;
    private Integer storeNumber;
    private Integer transactionNumber;
    private Date transactionDateTime;
    private String orderReferenceNumber;
    private int odometerAmount;
    private Double orderTotalAmount;

    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private ServiceCenter serviceCenter;
    private RecommendedService recommendedService;

    public RecommendedService getRecommendedService() {
        return recommendedService;
    }

    public void setRecommendedService(RecommendedService recommendedService) {
        this.recommendedService = recommendedService;
    }

    /**
     * @return the orderItems
     */
    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     *
     * @param orderItem the orderItem to add.
     */
    public void addOrderItems(final OrderItem orderItem) {
        Validate.notNull(orderItem, "The OrderItem cannot be null");
        if (this.orderItems == null) {
            this.orderItems = new ArrayList<OrderItem>();
        }
        this.orderItems.add(orderItem);
    }

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
    public int getOdometerAmount() {
        return odometerAmount;
    }

    /**
     * @param odometerAmount the odometerAmount to set
     */
    public void setOdometerAmount(int odometerAmount) {
        this.odometerAmount = odometerAmount;
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
     * @return the transactionDateTime
     */
    public Date getTransactionDateTime() {
        return transactionDateTime;
    }

    /**
     * @param transactionDateTime the transactionDateTime to set
     */
    public void setTransactionDateTime(Date transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public ServiceCenter getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(ServiceCenter serviceCenter) {
        this.serviceCenter = serviceCenter;
    }
}
