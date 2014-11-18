package com.searshc.mygarage.entities;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class OrderItem implements Serializable {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 4555155216527619816L;

    private String orderNumber;
    private String itemId;
    private Integer itemQuantity;
    private Double sellingPriceAmount;
    private String itemDescription;

    private String productFlag;

    private Double regularPrice;
    private Double pluPriceAmount;
    private Double itemCost;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        OrderItem rhs = (OrderItem) obj;
        return new EqualsBuilder()
                .append(this.getOrderNumber(), rhs.getOrderNumber())
                .append(this.getItemId(), rhs.getItemId())
                .append(this.getItemDescription(), rhs.getItemDescription()).isEquals();

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.getOrderNumber())
                .append(this.getItemId())
                .append(this.getItemDescription()).toHashCode();
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
     * @return the itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the itemQuantity
     */
    public Integer getItemQuantity() {
        return itemQuantity;
    }

    /**
     * @param itemQuantity the itemQuantity to set
     */
    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * @return the sellingPriceAmount
     */
    public Double getSellingPriceAmount() {
        return sellingPriceAmount;
    }

    /**
     * @param sellingPriceAmount the sellingPriceAmount to set
     */
    public void setSellingPriceAmount(Double sellingPriceAmount) {
        this.sellingPriceAmount = sellingPriceAmount;
    }

    /**
     * @return the itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @param itemDescription the itemDescription to set
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * @return the productFlag
     */
    public String getProductFlag() {
        return productFlag;
    }

    /**
     * @param productFlag the productFlag to set
     */
    public void setProductFlag(String productFlag) {
        this.productFlag = productFlag;
    }

    /**
     * @return the regularPrice
     */
    public Double getRegularPrice() {
        return regularPrice;
    }

    /**
     * @param regularPrice the regularPrice to set
     */
    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    /**
     * @return the pluPriceAmount
     */
    public Double getPluPriceAmount() {
        return pluPriceAmount;
    }

    /**
     * @param pluPriceAmount the pluPriceAmount to set
     */
    public void setPluPriceAmount(Double pluPriceAmount) {
        this.pluPriceAmount = pluPriceAmount;
    }

    /**
     * @return the itemCost
     */
    public Double getItemCost() {
        return itemCost;
    }

    /**
     * @param itemCost the itemCost to set
     */
    public void setItemCost(Double itemCost) {
        this.itemCost = itemCost;
    }

}
