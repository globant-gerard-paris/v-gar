package com.searshc.mygarage.apis.ncdb.response.order;

import com.searshc.mygarage.apis.ncdb.response.EsbMsg;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ESBMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderHistoryResponse extends EsbMsg {

    @XmlElement(name = "Data")
    private OrderDataResponse data;

    /**
     * @return the ordersHeader
     */
    public List<OrderHeaderResponse> getOrdersHeader() {
        return data != null ? data.getOrdersHeader() : new ArrayList<OrderHeaderResponse>();
    }

    /**
     * @return the orderItems
     */
    public List<OrderItemResponse> getOrderItems() {
        return data != null ? data.getOrderItems() : new ArrayList<OrderItemResponse>();
    }

    /**
     * @return the data
     */
    public OrderDataResponse getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(OrderDataResponse data) {
        this.data = data;
    }

}
