package com.searshc.mygarage.apis.ncdb.response.vehicle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.dozer.Mapping;

@XmlType(name = "vehicle_Typs")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleResponse {

    @XmlElement(name = "tng_id_no")
    @Mapping("tangibleId")
    private Integer tangibleIdNumber;

    @XmlElement(name = "tng_typ_cd")
    private String tangibleTypeCode;

    @XmlElement(name = "fam_id_no")
    @Mapping("familyId")
    private Integer familyIdNumber;

    @XmlElement(name = "fam_typ_cd")
    private String familyTypeCode;

    @XmlElement(name = "veh_yr_no")
    @Mapping("vehicle.year")
    private Integer vehicleYearNumber;

    @XmlElement(name = "veh_mak_ds")
    @Mapping("vehicle.make")
    private String vehicleMake;

    @XmlElement(name = "veh_mod_ds")
    @Mapping("vehicle.model")
    private String vehicleModel;

    @XmlElement(name = "veh_eng_ds")
    @Mapping("vehicle.engine")
    private String vehicleEngine;

    @XmlElement(name = "vin_no")
    private String vinNumber;

    @XmlElement(name = "vin_lst6_no")
    private String vinLast6Digits;

    @XmlElement(name = "ctg_id")
    private Integer catalogId;

    @XmlElement(name = "lic_plt_no")
    private String licensePlateNumber;

    @XmlElement(name = "lst_upd_ts")
    private String lastUpdateUserId;

    /**
     * If the value is 'N', it means that the vehicle is no longer owned
     */
    @XmlElement(name = "veh_sts")
    private String vehicleSts;

    /**
     * @return the tangibleIdNumber
     */
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
     * @return the vinNumber
     */
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
