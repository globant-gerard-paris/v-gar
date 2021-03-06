package com.searshc.mygarage.entities.record;

import java.io.Serializable;

public class ServiceCenter implements Serializable {

    private String sacStore;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;

    public ServiceCenter() {
    }

    /**
     * @return the sacStore
     */
    public String getSacStore() {
        return sacStore;
    }

    /**
     * @param sacStore the sacStore to set
     */
    public void setSacStore(String sacStore) {
        this.sacStore = sacStore;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
