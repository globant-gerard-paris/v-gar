package com.searshc.mygarage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * The {@link Store} represent the physical information of each store in the
 * system.
 *
 * @author Jero
 *
 */
@Entity
public class Store extends AbstractEntityStatefull {

    private String standing, address, city, state, phone, latitude, longitude;

    private boolean wifi;

    @Column(name = "district_number")
    private String districtNumber;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "region_number")
    private String regionNumber;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "store_type")
    private String storeType;

    @Column(name = "sac_store", nullable = false, unique = true)
    private String sacStore;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "store_manager")
    private String storeManager;

    @Column(name = "car_rental")
    private boolean carRental;

    @Column(name = "one_stop")
    private boolean oneStop;

    @Column(name = "monday_open")
    private String mondayOpen;

    @Column(name = "monday_close")
    private String mondayClose;

    @Column(name = "tuesday_open")
    private String tuesdayOpen;

    @Column(name = "tuesday_close")
    private String tuesdayClose;

    @Column(name = "wednesday_open")
    private String wednesdayOpen;

    @Column(name = "wednesday_close")
    private String wednesdayClose;

    @Column(name = "thursday_open")
    private String thursdayOpen;

    @Column(name = "thursday_close")
    private String thursdayClose;

    @Column(name = "friday_open")
    private String fridayOpen;

    @Column(name = "friday_close")
    private String fridayClose;

    @Column(name = "saturday_open")
    private String saturdayOpen;

    @Column(name = "saturday_close")
    private String saturdayClose;

    @Column(name = "sunday_open")
    private String sundayOpen;

    @Column(name = "sunday_close")
    private String sundayClose;

    public Store() {
    }

    public String getStanding() {
        return standing;
    }

    public void setStanding(String standing) {
        this.standing = standing;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String getDistrictNumber() {
        return districtNumber;
    }

    public void setDistrictNumber(String districtNumber) {
        this.districtNumber = districtNumber;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(String regionNumber) {
        this.regionNumber = regionNumber;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getSacStore() {
        return sacStore;
    }

    public void setSacStore(String sacStore) {
        this.sacStore = sacStore;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public boolean isCarRental() {
        return carRental;
    }

    public void setCarRental(boolean carRental) {
        this.carRental = carRental;
    }

    public boolean isOneStop() {
        return oneStop;
    }

    public void setOneStop(boolean oneStop) {
        this.oneStop = oneStop;
    }

    public String getMondayOpen() {
        return mondayOpen;
    }

    public void setMondayOpen(String mondayOpen) {
        this.mondayOpen = mondayOpen;
    }

    public String getMondayClose() {
        return mondayClose;
    }

    public void setMondayClose(String mondayClose) {
        this.mondayClose = mondayClose;
    }

    public String getTuesdayOpen() {
        return tuesdayOpen;
    }

    public void setTuesdayOpen(String tuesdayOpen) {
        this.tuesdayOpen = tuesdayOpen;
    }

    public String getTuesdayClose() {
        return tuesdayClose;
    }

    public void setTuesdayClose(String tuesdayClose) {
        this.tuesdayClose = tuesdayClose;
    }

    public String getWednesdayOpen() {
        return wednesdayOpen;
    }

    public void setWednesdayOpen(String wednesdayOpen) {
        this.wednesdayOpen = wednesdayOpen;
    }

    public String getWednesdayClose() {
        return wednesdayClose;
    }

    public void setWednesdayClose(String wednesdayClose) {
        this.wednesdayClose = wednesdayClose;
    }

    public String getThursdayOpen() {
        return thursdayOpen;
    }

    public void setThursdayOpen(String thursdayOpen) {
        this.thursdayOpen = thursdayOpen;
    }

    public String getThursdayClose() {
        return thursdayClose;
    }

    public void setThursdayClose(String thursdayClose) {
        this.thursdayClose = thursdayClose;
    }

    public String getFridayOpen() {
        return fridayOpen;
    }

    public void setFridayOpen(String fridayOpen) {
        this.fridayOpen = fridayOpen;
    }

    public String getFridayClose() {
        return fridayClose;
    }

    public void setFridayClose(String fridayClose) {
        this.fridayClose = fridayClose;
    }

    public String getSaturdayOpen() {
        return saturdayOpen;
    }

    public void setSaturdayOpen(String saturdayOpen) {
        this.saturdayOpen = saturdayOpen;
    }

    public String getSaturdayClose() {
        return saturdayClose;
    }

    public void setSaturdayClose(String saturdayClose) {
        this.saturdayClose = saturdayClose;
    }

    public String getSundayOpen() {
        return sundayOpen;
    }

    public void setSundayOpen(String sundayOpen) {
        this.sundayOpen = sundayOpen;
    }

    public String getSundayClose() {
        return sundayClose;
    }

    public void setSundayClose(String sundayClose) {
        this.sundayClose = sundayClose;
    }

}
