package com.searshc.mygarage.entities;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class OrderItem implements Serializable{

    /**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 4555155216527619816L;
	
	private String orderNumber;
    private String itemId;
    private String itemDescription;


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
    		.append(this.orderNumber, rhs.orderNumber)
    		.append(this.itemId, rhs.itemId)
    		.append(this.itemDescription, rhs.itemDescription).isEquals();
    }
    
    @Override
    public int hashCode() {
    	return new HashCodeBuilder()
    		.append(this.orderNumber)
    		.append(this.itemId)
    		.append(this.itemDescription).toHashCode();
    }

}
