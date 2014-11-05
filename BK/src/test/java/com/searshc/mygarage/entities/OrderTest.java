package com.searshc.mygarage.entities;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class OrderTest {
	
	private final String ORDER_NUMBER = "132710172";
	private final String TRANSACTION_DATE = "2012-06-22";
	private final String TRANSACTION_LOCAL_TIME = "13:10:59";
	private final Double ODOMETER = Double.valueOf(22222);
	
	@Test
	public void createOrderHappyPath() {
		Order order = new Order();
		order.setOrderNumber(ORDER_NUMBER);
		order.setTransactionDate(TRANSACTION_DATE);
		order.setTransactionLocalTime(TRANSACTION_LOCAL_TIME);
		order.setOdometerNumber(ODOMETER);
		
		assertTrue(order.getOrderNumber().equalsIgnoreCase(ORDER_NUMBER));
		assertTrue(order.getTransactionDate().equalsIgnoreCase(TRANSACTION_DATE));
		assertTrue(order.getTransactionLocalTime().equalsIgnoreCase(TRANSACTION_LOCAL_TIME));
		assertTrue(order.getOdometerNumber().equals(new Double(ODOMETER)));
		assertTrue(order.getOrderItems().size() == 0);
	}
	
	@Test
	public void createAnOrderWithOrderItems() {
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setItemId("132710172");
		orderItem1.setItemDescription("BTRY,DHPLATINUM 34/78 DT");
		orderItem1.setOrderNumber(ORDER_NUMBER);
		
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setItemId("132710172");
		orderItem2.setItemDescription("BATTERY CORE,RECYCLE CHRG");
		orderItem2.setOrderNumber(ORDER_NUMBER);
		
		Order order = new Order();
		order.addOrderItems(orderItem1);
		order.addOrderItems(orderItem2);
		
		assertTrue(order.getOrderItems().size() == 2);
		assertTrue(order.getOrderItems().get(0).equals(orderItem1));
		assertTrue(order.getOrderItems().get(1).equals(orderItem2));
		
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldntAddNullOrderItem() {
		Order order = new Order();
		order.addOrderItems(null);
	}
	
	@Test
	public void addListOfOrderItems() {
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setItemId("132710172");
		orderItem1.setItemDescription("BTRY,DHPLATINUM 34/78 DT");
		orderItem1.setOrderNumber(ORDER_NUMBER);
		
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setItemId("132710172");
		orderItem2.setItemDescription("BATTERY CORE,RECYCLE CHRG");
		orderItem2.setOrderNumber(ORDER_NUMBER);
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(orderItem1);
		orderItems.add(orderItem2);
		
		Order order = new Order();
		order.setOrderItems(orderItems);
		
		assertTrue(order.getOrderItems().size() == 2);
		assertTrue(order.getOrderItems().get(0).equals(orderItem1));
		assertTrue(order.getOrderItems().get(1).equals(orderItem2));
	}
}
