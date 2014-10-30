package com.searshc.mygarage.apis.ncdb.response.order;

import com.searshc.mygarage.apis.ncdb.response.EsbMsg;
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
