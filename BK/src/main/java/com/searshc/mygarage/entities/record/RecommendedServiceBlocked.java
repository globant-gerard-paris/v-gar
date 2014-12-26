package com.searshc.mygarage.entities.record;

import com.searshc.mygarage.entities.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author rammel.maestre
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"orderNumber", "familyIdNumber", "tangibleIdNumber", "sku"}))
public class RecommendedServiceBlocked extends AbstractEntity {

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private Long familyIdNumber;

    @Column(nullable = false)
    private Long tangibleIdNumber;

    @Column(nullable = false)
    private String sku;

    public RecommendedServiceBlocked() {

    }

    public RecommendedServiceBlocked(String orderNumber, Long familyIdNumber, Long tangibleIdNumber, String sku) {
        this.orderNumber = orderNumber;
        this.familyIdNumber = familyIdNumber;
        this.tangibleIdNumber = tangibleIdNumber;
        this.sku = sku;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

}
