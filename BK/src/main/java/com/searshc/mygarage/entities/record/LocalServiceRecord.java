package com.searshc.mygarage.entities.record;

import java.util.Date;

public class LocalServiceRecord extends ServiceRecord {

	private Long id;
    private String service;

    public LocalServiceRecord() {
    }

    public LocalServiceRecord(Long id, String service, Double mileage, Date date, ServiceCenter serviceCenter) {
        super(mileage, date, serviceCenter);
        ServiceRecordItem item = new ServiceRecordItem();
        item.setDescription(service);
        this.addServiceRecordItem(item);
        this.id = id;
        this.service = service;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
