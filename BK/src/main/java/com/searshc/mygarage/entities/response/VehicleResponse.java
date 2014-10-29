package com.searshc.mygarage.entities.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "vehicle_Typs")
public class VehicleResponse {

    private Integer tangibleIdNumber;
    private String tangibleTypeCode;
    private Integer familyIdNumber;
    private String familyTypeCode;
    private Integer vehicleYearNumber;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleEngine;
    private String vehicleColor;
    private String vinNumber;
    private String vinLast6Digits;
    private Integer catalogId;
    private String licensePlateNumber;
    private String lastUpdateUserId;    
    
    /**
     *  If the value is “N”, it means that the vehicle is no longer owned
     */
    private String vehicleSts;

    /**
     * @return the tangibleIdNumber
     */
    @XmlElement(name = "tng_id_no")
    public Integer getTangibleIdNumber() {
        return tangibleIdNumber;
    }

    /**
     * @param tangibleIdNumber the tangibleIdNumber to set
     */
    public void setTangibleIdNumber(Integer tangibleIdNumber) {
        this.tangibleIdNumber = tangibleIdNumber;
    }

    /**
     * @return the tangibleTypeCode
     */
    @XmlElement(name = "tng_typ_cd")
    public String getTangibleTypeCode() {
        return tangibleTypeCode;
    }

    /**
     * @param tangibleTypeCode the tangibleTypeCode to set
     */
    public void setTangibleTypeCode(String tangibleTypeCode) {
        this.tangibleTypeCode = tangibleTypeCode;
    }

    /**
     * @return the familyIdNumber
     */
    @XmlElement(name = "fam_id_no")
    public Integer getFamilyIdNumber() {
        return familyIdNumber;
    }

    /**
     * @param familyIdNumber the familyIdNumber to set
     */
    public void setFamilyIdNumber(Integer familyIdNumber) {
        this.familyIdNumber = familyIdNumber;
    }

    /**
     * @return the familyTypeCode
     */
    @XmlElement(name = "fam_typ_cd")
    public String getFamilyTypeCode() {
        return familyTypeCode;
    }

    /**
     * @param familyTypeCode the familyTypeCode to set
     */
    public void setFamilyTypeCode(String familyTypeCode) {
        this.familyTypeCode = familyTypeCode;
    }

    /**
     * @return the vehicleYearNumber
     */
    @XmlElement(name = "veh_yr_no")
    public Integer getVehicleYearNumber() {
        return vehicleYearNumber;
    }

    /**
     * @param vehicleYearNumber the vehicleYearNumber to set
     */
    public void setVehicleYearNumber(Integer vehicleYearNumber) {
        this.vehicleYearNumber = vehicleYearNumber;
    }

    /**
     * @return the vehicleMake
     */
    @XmlElement(name = "veh_mak_ds")
    public String getVehicleMake() {
        return vehicleMake;
    }

    /**
     * @param vehicleMake the vehicleMake to set
     */
    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    /**
     * @return the vehicleModel
     */
    @XmlElement(name = "veh_mod_ds")
    public String getVehicleModel() {
        return vehicleModel;
    }

    /**
     * @param vehicleModel the vehicleModel to set
     */
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    /**
     * @return the vehicleEngine
     */
    @XmlElement(name = "veh_eng_ds")
    public String getVehicleEngine() {
        return vehicleEngine;
    }

    /**
     * @param vehicleEngine the vehicleEngine to set
     */
    public void setVehicleEngine(String vehicleEngine) {
        this.vehicleEngine = vehicleEngine;
    }

    /**
     * @return the vehicleColor
     */
    @XmlElement(name = "veh_col_ds")
    public String getVehicleColor() {
        return vehicleColor;
    }

    /**
     * @param vehicleColor the vehicleColor to set
     */
    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    /**
     * @return the vinNumber
     */
    @XmlElement(name = "vin_no")
    public String getVinNumber() {
        return vinNumber;
    }

    /**
     * @param vinNumber the vinNumber to set
     */
    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    /**
     * @return the vinLast6Digits
     */
    @XmlElement(name = "vin_lst6_no")
    public String getVinLast6Digits() {
        return vinLast6Digits;
    }

    /**
     * @param vinLast6Digits the vinLast6Digits to set
     */
    public void setVinLast6Digits(String vinLast6Digits) {
        this.vinLast6Digits = vinLast6Digits;
    }

    /**
     * @return the catalogId
     */
    @XmlElement(name = "ctg_id")
    public Integer getCatalogId() {
        return catalogId;
    }

    /**
     * @param catalogId the catalogId to set
     */
    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    /**
     * @return the licensePlateNumber
     */
    @XmlElement(name = "lic_plt_no")
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    /**
     * @param licensePlateNumber the licensePlateNumber to set
     */
    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    /**
     * @return the lastUpdateUserId
     */
    @XmlElement(name = "lst_upd_ts")
    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    /**
     * @param lastUpdateUserId the lastUpdateUserId to set
     */
    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    /**
     * @return the vehicleSts
     */
    public String getVehicleSts() {
        return vehicleSts;
    }

    /**
     * @param vehicleSts the vehicleSts to set
     */
    public void setVehicleSts(String vehicleSts) {
        this.vehicleSts = vehicleSts;
    }

}