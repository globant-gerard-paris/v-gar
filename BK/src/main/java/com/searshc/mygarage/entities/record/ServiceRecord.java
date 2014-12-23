package com.searshc.mygarage.entities.record;

import com.searshc.mygarage.util.ServiceRecordType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ServiceRecord {

    protected int mileage;
    protected Date date;
    protected ServiceRecordType type;

    protected List<ServiceRecordItem> serviceRecordItems = new ArrayList<ServiceRecordItem>();

    public ServiceRecord() {
    }

    public ServiceRecord(int mileage, Date date, ServiceRecordType type) {
        this.mileage = mileage;
        this.date = date;
        this.type = type;
    }

    public ServiceRecord(int mileage, Date date, ServiceRecordType type, ServiceRecordItem serviceRecordItem) {
        this(mileage, date, type);
        this.serviceRecordItems.add(serviceRecordItem);
    }

    public void addServiceRecordItem(ServiceRecordItem item) {
        if(this.serviceRecordItems == null) {
        	this.serviceRecordItems = new ArrayList<ServiceRecordItem>();
        }
    	this.serviceRecordItems.add(item);
    }

    /**
     * @return the mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(int mileage) {
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

    /**
     * @return the type
     */
    public ServiceRecordType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ServiceRecordType type) {
        this.type = type;
    }

}
