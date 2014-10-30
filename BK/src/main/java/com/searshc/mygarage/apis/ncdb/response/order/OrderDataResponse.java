package com.searshc.mygarage.apis.ncdb.response.order;

import com.searshc.mygarage.apis.ncdb.response.DataResponse;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDataResponse extends DataResponse {

    @XmlElement(name = "Order")
    private OrdersResponse orders;

    /**
     * @return the orders
     */
    public OrdersResponse getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(OrdersResponse orders) {
        this.orders = orders;
    }

}
