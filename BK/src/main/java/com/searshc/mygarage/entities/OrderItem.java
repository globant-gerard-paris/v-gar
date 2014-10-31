package com.searshc.mygarage.entities;

public class OrderItem {

    private String orderNumber;
//    private String orderLineNumber;
//    private String lineItemType;
    private String itemId;
//    private String itemQuantity;
//    private String sellingPriceAmount;
//    private String regularPrice;
//    private String pluPriceAmount;
    private String itemDescription;
//    private String sellingAssociateId;
//    private String itemTaxAmount;
//    private String restockingFeeEligible;
//    private String productFlag;
//    private String miscAcctNumber;

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

}
