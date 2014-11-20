package com.searshc.mygarage.dtos;

import java.util.Date;

public class LocalServiceRecord extends ServiceRecord {
    
    private String service;
    
    public LocalServiceRecord() {
    }
    
    public LocalServiceRecord(String service, Double mileage, Date date, ServiceCenter serviceCenter) {
        super(mileage, date, serviceCenter);
        ServiceRecordItem item = new ServiceRecordItem();
        item.setDescription(service);
        this.addServiceRecordItem(item);
        this.service = service;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }
    
}
