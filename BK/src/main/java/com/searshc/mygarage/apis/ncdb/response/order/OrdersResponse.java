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

    @XmlElement(name = "order_Header")
    private List<OrderHeaderResponse> ordersHeader = new ArrayList<OrderHeaderResponse>();

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

    /**
     * @return the ordersHeader
     */
    public List<OrderHeaderResponse> getOrdersHeader() {
        return ordersHeader;
    }

    /**
     * @param ordersHeader the ordersHeader to set
     */
    public void setOrdersHeader(List<OrderHeaderResponse> ordersHeader) {
        this.ordersHeader = ordersHeader;
    }

}
