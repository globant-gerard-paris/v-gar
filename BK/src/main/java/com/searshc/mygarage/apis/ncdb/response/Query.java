package com.searshc.mygarage.apis.ncdb.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Query")
@XmlAccessorType(XmlAccessType.FIELD)
public class Query {

    @XmlElement(name = "fam_id_no")
    private Long familyIdNumber;

    @XmlElement(name = "fam_typ_cd")
    private String familyTypeCode = "R";

    @XmlElement(name = "tng_id_no1")
    private Long tangibleIdNumber;

    public Query() {
    }

    public Query(Long familyIdNumber) {
        this.familyIdNumber = familyIdNumber;
    }

    public Query(Long familyIdNumber, Long tangibleIdNumber) {
        this.familyIdNumber = familyIdNumber;
        this.tangibleIdNumber = tangibleIdNumber;
    }

    /**
     * @return the familyIdNumber
     */
    public Long getFamilyIdNumber() {
        return familyIdNumber;
    }

    /**
     * @param familyIdNumber the familyIdNumber to set
     */
    public void setFamilyIdNumber(Long familyIdNumber) {
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
    public Long getTangibleIdNumber() {
        return tangibleIdNumber;
    }

    /**
     * @param tangibleIdNumber the tangibleIdNumber to set
     */
    public void setTangibleIdNumber(Long tangibleIdNumber) {
        this.tangibleIdNumber = tangibleIdNumber;
    }

}
