package com.searshc.mygarage.entities.record;

public class RecommendedService extends NcdbServiceRecord {

    private boolean valid;

    public RecommendedService(boolean valid, Order order, ServiceCenter serviceCenter) {
        super(order, serviceCenter);
        this.valid = valid;
    }

    /**
     * @return the valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @param valid the valid to set
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
