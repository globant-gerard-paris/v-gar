package com.searshc.mygarage.entities.record;

public class NcdbServiceRecord extends ServiceRecord {

    protected Order order;

    public NcdbServiceRecord() {
    }

    public NcdbServiceRecord(Order order, ServiceCenter serviceCenter) {
        super(order.getOdometerAmount(), order.getTransactionDateTime(), serviceCenter);
        this.order = order;
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

}
