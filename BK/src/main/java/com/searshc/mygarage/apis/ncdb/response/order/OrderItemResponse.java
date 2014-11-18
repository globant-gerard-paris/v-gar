package com.searshc.mygarage.apis.ncdb.response.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "order_Items")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItemResponse {

    @XmlElement(name = "ord_no")
    private String orderNumber;

    @XmlElement(name = "ord_ln_no")
    private Integer orderLineNumber;

    @XmlElement(name = "ln_itm_typ")
    private String lineItemType;

    @XmlElement(name = "itm_id")
    private String itemId;

    @XmlElement(name = "itm_qt")
    private Integer itemQuantity;

    @XmlElement(name = "sll_prc_am")
    private Double sellingPriceAmount;

    @XmlElement(name = "reg_prc_am")
    private Double regularPrice;

    @XmlElement(name = "plu_prc_am")
    private Double pluPriceAmount;

    @XmlElement(name = "itm_cst_am")
    private Double itemCost;

    @XmlElement(name = "itm_ds")
    private String itemDescription;

    @XmlElement(name = "sll_asc_id_no")
    private String sellingAssociateId;

    @XmlElement(name = "itm_tax_am")
    private Double itemTaxAmount;

    @XmlElement(name = "rsk_fee_elg_cd")
    private String restockingFeeEligible;

    @XmlElement(name = "prd_fl")
    private String productFlag;

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
     * @return the orderLineNumber
     */
    public Integer getOrderLineNumber() {
        return orderLineNumber;
    }

    /**
     * @param orderLineNumber the orderLineNumber to set
     */
    public void setOrderLineNumber(Integer orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    /**
     * @return the lineItemType
     */
    public String getLineItemType() {
        return lineItemType;
    }

    /**
     * @param lineItemType the lineItemType to set
     */
    public void setLineItemType(String lineItemType) {
        this.lineItemType = lineItemType;
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
     * @return the sellingAssociateId
     */
    public String getSellingAssociateId() {
        return sellingAssociateId;
    }

    /**
     * @param sellingAssociateId the sellingAssociateId to set
     */
    public void setSellingAssociateId(String sellingAssociateId) {
        this.sellingAssociateId = sellingAssociateId;
    }

    /**
     * @return the itemTaxAmount
     */
    public Double getItemTaxAmount() {
        return itemTaxAmount;
    }

    /**
     * @param itemTaxAmount the itemTaxAmount to set
     */
    public void setItemTaxAmount(Double itemTaxAmount) {
        this.itemTaxAmount = itemTaxAmount;
    }

    /**
     * @return the restockingFeeEligible
     */
    public String getRestockingFeeEligible() {
        return restockingFeeEligible;
    }

    /**
     * @param restockingFeeEligible the restockingFeeEligible to set
     */
    public void setRestockingFeeEligible(String restockingFeeEligible) {
        this.restockingFeeEligible = restockingFeeEligible;
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

}
