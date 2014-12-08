package com.searshc.mygarage.entities.record;

import com.searshc.mygarage.util.ServiceRecordType;

public class NcdbServiceRecord extends ServiceRecord {

    private Order order;

    private ServiceCenter serviceCenter;

    public NcdbServiceRecord() {
    }

    public NcdbServiceRecord(Order order, ServiceCenter serviceCenter) {
        super(order.getOdometerAmount(), order.getTransactionDateTime(), ServiceRecordType.REMOTE_SERVICE);
        this.order = order;
        this.serviceCenter = serviceCenter;
    }

    public NcdbServiceRecord(Order order, ServiceCenter serviceCenter, ServiceRecordItem serviceRecordItem) {
        this(order, serviceCenter);
        this.addServiceRecordItem(serviceRecordItem);
    }

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the serviceCenter
     */
    public ServiceCenter getServiceCenter() {
        return serviceCenter;
    }

    /**
     * @param serviceCenter the serviceCenter to set
     */
    public void setServiceCenter(ServiceCenter serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

}
