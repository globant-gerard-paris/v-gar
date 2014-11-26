package com.searshc.mygarage.entities;

import com.searshc.mygarage.entities.record.OrderItem;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class OrderItemTest {

    private final String ORDER_NUMBER = "132710172";
    private final String ITEM_ID = "22811256000";
    private final String ITEM_DESCRIPTION = "BATTERY CORE,RECYCLE CHRG";

    @Test
    public void orderItemCreationHappyPath() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderNumber(ORDER_NUMBER);
        orderItem.setItemId(ITEM_ID);
        orderItem.setItemDescription(ITEM_DESCRIPTION);

        assertEquals(orderItem.getOrderNumber(), ORDER_NUMBER);
        assertEquals(orderItem.getItemId(), ITEM_ID);
        assertEquals(orderItem.getItemDescription(), ITEM_DESCRIPTION);
    }

    @Test
    public void sameObjects() {
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrderNumber(ORDER_NUMBER);
        orderItem1.setItemId(ITEM_ID);
        orderItem1.setItemDescription(ITEM_DESCRIPTION);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setOrderNumber(ORDER_NUMBER);
        orderItem2.setItemId(ITEM_ID);
        orderItem2.setItemDescription(ITEM_DESCRIPTION);

    }

    @Test
    public void compareToNullObject() {
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrderNumber(ORDER_NUMBER);
        orderItem1.setItemId(ITEM_ID);
        orderItem1.setItemDescription(ITEM_DESCRIPTION);

        Assert.assertTrue(orderItem1.equals(null) == false);
    }

    @Test
    public void compareToOtherClassObject() {
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrderNumber(ORDER_NUMBER);
        orderItem1.setItemId(ITEM_ID);
        orderItem1.setItemDescription(ITEM_DESCRIPTION);

        Assert.assertTrue(orderItem1.equals(new String("something")) == false);
    }
}
