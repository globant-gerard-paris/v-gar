package com.searshc.mygarage.entities;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String orderNumber;
//    private String customerIdNumber;
//    private String tangibleIdNumber;
//    private String storeNumber;
//    private String registerNumber;
//    private String transactionNumber;
    private String transactionDate;
    private String transactionLocalTime;
//    private String orderReferenceNumber;
    private Double odometerNumber;
//    private String orderOriginationCode;
//    private String orderTotalAmount;
//    private String ringingAssociateId;
//    private String loyaltyIdNumber;
//    private String orderCommentText;
//    private String familyIdNumber;
    private List<OrderItem> orderItems;

    /**
     * @return the orderItems
     */
    public List<OrderItem> getOrderItems() {
        return orderItems != null ? this.orderItems : new ArrayList<OrderItem>();
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
