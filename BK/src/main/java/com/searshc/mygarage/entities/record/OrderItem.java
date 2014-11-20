package com.searshc.mygarage.entities.record;

import com.searshc.mygarage.util.ServiceRecordType;

public class OrderItem {

    private String orderNumber;
    private String itemId;
    private Integer itemQuantity;
    private Double sellingPriceAmount;
    private String itemDescription;

    private Integer productFlag;

    private Double regularPrice;
    private Double pluPriceAmount;
    private Double itemCost;

    private ServiceRecordType type;

    @Override
    public String toString() {
        return itemDescription;
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
    public Integer getProductFlag() {
        return productFlag;
    }

    /**
     * @param productFlag the productFlag to set
     */
    public void setProductFlag(Integer productFlag) {
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

    /**
     * @return the type
     */
    public ServiceRecordType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ServiceRecordType type) {
        this.type = type;
    }

}
