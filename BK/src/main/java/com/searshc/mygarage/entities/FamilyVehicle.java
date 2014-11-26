package com.searshc.mygarage.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "family_vehicle")
public class FamilyVehicle extends AbstractEntity implements Serializable {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -650535603949006577L;

    /**
     * The {@link Vehicle}. Required.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    /**
     * The NCDB Family Id. Optional. A value indicates that the vehicle belongs
     * to a family instead an individual user.
     */
    @Column(name = "family_id", nullable = true)
    private Long familyId;

    /**
     * The Tangible Id. Optional. It is the id used in NCDB to identify uniquely
     * a vehicle. A value indicates that the vehicle is registered in NCDB,
     * otherwise is a local vehicle.
     */
    @Column(name = "tangible_id", nullable = true)
    private Long tangibleId;

    //TODO: change the String to another data type
    @Column(name = "color")
    private String color;

    @Column(name = "mileage", nullable = false)
    private int mileage;

    /**
     * The FamilyVehicle name
     */
    @Column(name = "name")
    private String name;

    public FamilyVehicle() {
        this.vehicle = null;
        this.familyId = null;
        this.tangibleId = null;
        this.color = "";
        this.mileage = 0;
        this.name = null;
    }

    /**
     * @param vehicle: Required
     * @param familyId: Optional
     * @param tangibleId: Optional
     * @param color: Required
     * @param mileage: Optional
     * @param name: Optional
     */
    public FamilyVehicle(final Vehicle vehicle, final Long familyId, final Long tangibleId, final String color, final int mileage, final String name) {
        this.vehicle = Validate.notNull(vehicle, "The Vehicle cannot be null");
        this.familyId = familyId;
        this.tangibleId = tangibleId;
        this.color = color;
        Validate.isTrue(mileage > 0, "The Mileage cannot be lower than 0");
        this.mileage = mileage;
        this.name = name;
    }

    /**
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @param vehicle the vehicle to set
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = Validate.notNull(vehicle, "The Vehicle cannot be null");
    }

    /**
     * @return the familyId
     */
    public Long getFamilyId() {
        return familyId;
    }

    /**
     * @param familyId the familyId to set
     */
    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    /**
     * @return the tangibleId
     */
    public Long getTangibleId() {
        return tangibleId;
    }

    /**
     * @param tangibleId the tangibleId to set
     */
    public void setTangibleId(Long tangibleId) {
        this.tangibleId = tangibleId;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(int mileage) {
    	Validate.isTrue(mileage >= 0, "The Mileage cannot be lower than 0");
    	this.mileage = mileage;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        		.append(this.vehicle)
                .append(this.familyId)
                .append(this.tangibleId)
                .append(this.color).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FamilyVehicle rhs = (FamilyVehicle) obj;
        return new EqualsBuilder()
                .append(this.vehicle, rhs.vehicle)
                .append(this.familyId, rhs.familyId)
                .append(this.tangibleId, rhs.tangibleId)
                .append(this.color, rhs.color).isEquals();
    }
}
