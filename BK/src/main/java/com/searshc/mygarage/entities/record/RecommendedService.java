package com.searshc.mygarage.entities.record;

import java.util.ArrayList;
import java.util.List;

public class RecommendedService extends NcdbServiceRecord {

    private List<ServiceRecordItem> previousServiceRecordItems = new ArrayList<ServiceRecordItem>();

    public RecommendedService() {
    }

    public RecommendedService(Order order, ServiceCenter serviceCenter) {
        super(order, serviceCenter);
    }

    public void addPreviousServiceRecordItem(ServiceRecordItem item) {
        this.getPreviousServiceRecordItems().add(item);
    }

    /**
     * @return the previousServiceRecordItems
     */
    public List<ServiceRecordItem> getPreviousServiceRecordItems() {
        return previousServiceRecordItems;
    }

    /**
     * @param previousServiceRecordItems the previousServiceRecordItems to set
     */
    public void setPreviousServiceRecordItems(List<ServiceRecordItem> previousServiceRecordItems) {
        this.previousServiceRecordItems = previousServiceRecordItems;
    }

}
