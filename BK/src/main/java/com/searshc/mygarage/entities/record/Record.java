package com.searshc.mygarage.entities.record;

import com.searshc.mygarage.entities.AbstractEntity;
import com.searshc.mygarage.entities.FamilyVehicle;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * The {@link Record} is the historic registered services that were performed in
 * the car.
 *
 * @author Jero
 *
 */
@Entity
public class Record extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private FamilyVehicle familyVehicle;

    private int mileage;

    private String source;

    /**
     * The description of the {@code service} of the record.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private SuggestedService suggestedService;

    /**
     * The {@code date} of the record.
     */
    private Date date;

    /**
     * A technical detail of what was done to the car.
     */
    @Column(columnDefinition = "text")
    private String comment;

    public Record() {        
        this.familyVehicle = null;
    }

    /**
     * @param familyVehicle
     * @param mileage
     * @param source
     * @param suggestedService
     * @param date
     * @param comment
     */
    public Record(FamilyVehicle familyVehicle, int mileage, String source,
            SuggestedService suggestedService, Date date, String comment) {
        super();
        this.familyVehicle = familyVehicle;
        this.mileage = mileage;
        this.source = source;
        this.suggestedService = suggestedService;
        this.date = date;
        this.comment = comment;
    }

    /**
     * @return the familyVehicle
     */
    public FamilyVehicle getFamilyVehicle() {
        return familyVehicle;
    }

    /**
     * @param familyVehicle the familyVehicle to set
     */
    public void setFamilyVehicle(FamilyVehicle familyVehicle) {
        this.familyVehicle = familyVehicle;
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
        this.mileage = mileage;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the suggestedService
     */
    public SuggestedService getSuggestedService() {
        return suggestedService;
    }

    /**
     * @param suggestedService the suggestedService to set
     */
    public void setSuggestedService(SuggestedService suggestedService) {
        this.suggestedService = suggestedService;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
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
        Record rhs = (Record) obj;
        return new EqualsBuilder()
                .append(this.getFamilyVehicle(), rhs.getFamilyVehicle())
                .append(this.getMileage(), rhs.getMileage())
                .append(this.getSource(), rhs.getSource())
                .append(this.getSuggestedService(), rhs.getSuggestedService())
                .append(this.getDate(), rhs.getDate())
                .append(this.getComment(), rhs.getComment()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.getFamilyVehicle())
                .append(this.getMileage())
                .append(this.getSource())
                .append(this.getSuggestedService())
                .append(this.getDate())
                .append(this.getComment()).hashCode();
    }
}
