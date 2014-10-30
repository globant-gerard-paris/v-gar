package com.searshc.mygarage.apis.ncdb.response.order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrdersResponse {

    @XmlElement(name = "order_Items")
    private List<OrderItemResponse> orderItems = new ArrayList<OrderItemResponse>();

    /**
     * @return the orderItems
     */
    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }

}
