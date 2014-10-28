package com.searshc.mygarage.entities;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private String orderNumber;
	private String customerIdNumber;
	private String tangibleIdNumber;
	private String storeNumber;
	private String registerNumber;
	private String transactionNumber;
	private String transactionDate;
	private String transactionLocalTime;
	private String orderReferenceNumber;
	private String odometerNumber;
	private String orderOriginationCode;
	private String orderTotalAmount;
	private String ringingAssociateId;
	private String loyaltyIdNumber;
	private String orderCommentText;
	private String familyIdNumber;
	private List<OrderItem> orderItems;
	
	
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
	public String getTangibleIdNumber() {
		return tangibleIdNumber;
	}
	/**
	 * @param tangibleIdNumber the tangibleIdNumber to set
	 */
	public void setTangibleIdNumber(String tangibleIdNumber) {
		this.tangibleIdNumber = tangibleIdNumber;
	}
	/**
	 * @return the storeNumber
	 */
	public String getStoreNumber() {
		return storeNumber;
	}
	/**
	 * @param storeNumber the storeNumber to set
	 */
	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}
	/**
	 * @return the registerNumber
	 */
	public String getRegisterNumber() {
		return registerNumber;
	}
	/**
	 * @param registerNumber the registerNumber to set
	 */
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	/**
	 * @return the transactionNumber
	 */
	public String getTransactionNumber() {
		return transactionNumber;
	}
	/**
	 * @param transactionNumber the transactionNumber to set
	 */
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
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
	 * @return the odometerNumber
	 */
	public String getOdometerNumber() {
		return odometerNumber;
	}
	/**
	 * @param odometerNumber the odometerNumber to set
	 */
	public void setOdometerNumber(String odometerNumber) {
		this.odometerNumber = odometerNumber;
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
	public String getOrderTotalAmount() {
		return orderTotalAmount;
	}
	/**
	 * @param orderTotalAmount the orderTotalAmount to set
	 */
	public void setOrderTotalAmount(String orderTotalAmount) {
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
	/**
	 * @return the familyIdNumber
	 */
	public String getFamilyIdNumber() {
		return familyIdNumber;
	}
	/**
	 * @param familyIdNumber the familyIdNumber to set
	 */
	public void setFamilyIdNumber(String familyIdNumber) {
		this.familyIdNumber = familyIdNumber;
	}
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
		if(this.orderItems == null) {
			this.orderItems = new ArrayList<OrderItem>();
		}
		this.orderItems.add(orderItem);
	}
}
