package com.searshc.mygarage.apis.ncdb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.searshc.mygarage.entities.Order;
import com.searshc.mygarage.entities.OrderItem;

@Component
public class NCDBApiMock implements NCDBApi {

	public List<Order> getCarTransactionHistory(final String familyIdNumber, final String tangibleId) {
		List<Order> response = new ArrayList<Order>();
		
		Order order = new Order();
		order.setOrderNumber("132710172");
		order.setCustomerIdNumber("36811642");
		order.setTangibleIdNumber("132622115");
		order.setStoreNumber("6542");
		order.setRegisterNumber("79");
		order.setTransactionNumber("2");
		order.setTransactionDate("2012-06-22");
		order.setTransactionLocalTime("13:10:59");
		order.setOrderReferenceNumber("7900001");
		order.setOdometerNumber("0");
		order.setOrderOriginationCode("");
		order.setOrderTotalAmount("249.09");
		order.setRingingAssociateId("18");
		order.setLoyaltyIdNumber("0");
		order.setOrderCommentText("");
		order.setFamilyIdNumber("73311110");
		
		OrderItem item1 = new OrderItem();
		item1.setOrderNumber("132710172");
		item1.setOrderLineNumber("1");
		item1.setLineItemType("S");
		item1.setItemId("22850090000");
		item1.setItemQuantity("1");
		item1.setSellingPriceAmount("219.99");
		item1.setRegularPrice("219.99");
		item1.setPluPriceAmount("219.99");
		item1.setItemDescription("BTRY,DHPLATINUM 34/78 DT");
		item1.setSellingAssociateId("18");
		item1.setItemTaxAmount("13.20");
		item1.setRestockingFeeEligible("NO");
		item1.setProductFlag("2");
		item1.setMiscAcctNumber("0");
		
		OrderItem item2 = new OrderItem();
		item2.setOrderNumber("132710172");
		item2.setOrderLineNumber("2");
		item2.setLineItemType("S");
		item2.setItemId("22811256000");
		item2.setItemQuantity("1");
		item2.setSellingPriceAmount("15.00");
		item2.setRegularPrice("15.00");
		item2.setPluPriceAmount("15.00");
		item2.setItemDescription("BATTERY CORE,RECYCLE CHRG");
		item2.setSellingAssociateId("18");
		item2.setItemTaxAmount("0.90");
		item2.setRestockingFeeEligible("NO");
		item2.setProductFlag("0");
		item2.setMiscAcctNumber("0");
		
		order.addOrderItems(item1);
		order.addOrderItems(item2);
		
		response.add(order);
		
		return response;
	}
}
