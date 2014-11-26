package com.searshc.mygarage.entities.record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ServiceRecord {

    protected Double mileage;
    protected Date date;
    protected ServiceCenter serviceCenter;

    private List<ServiceRecordItem> serviceRecordItems = new ArrayList<ServiceRecordItem>();

    public ServiceRecord() {
    }

    public ServiceRecord(Double mileage, Date date, ServiceCenter serviceCenter) {
        this.mileage = mileage;
        this.date = date;
        this.serviceCenter = serviceCenter;
    }

    public void addServiceRecordItem(ServiceRecordItem item) {
        this.serviceRecordItems.add(item);
    }

    /**
     * @return the mileage
     */
    public Double getMileage() {
        return mileage;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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

    /**
     * @return the serviceRecordItems
     */
    public List<ServiceRecordItem> getServiceRecordItems() {
        return serviceRecordItems;
    }

    /**
     * @param serviceRecordItems the serviceRecordItems to set
     */
    public void setServiceRecordItems(List<ServiceRecordItem> serviceRecordItems) {
        this.serviceRecordItems = serviceRecordItems;
    }

}
