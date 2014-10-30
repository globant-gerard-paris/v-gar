package com.searshc.mygarage.apis.ncdb.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Query")
@XmlAccessorType(XmlAccessType.FIELD)
public class Query {

    @XmlElement(name = "fam_id_no")
    private Integer familyIdNumber;

    @XmlElement(name = "fam_typ_cd")
    private String familyTypeCode = "R";

    @XmlElement(name = "tng_id_no1")
    private Integer tangibleIdNumber;

    public Query() {
    }

    public Query(Integer familyIdNumber) {
        this.familyIdNumber = familyIdNumber;
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

}
