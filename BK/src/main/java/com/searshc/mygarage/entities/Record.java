/**
 *
 */
package com.searshc.mygarage.entities;

import java.util.Date;

import javax.persistence.CascadeType;
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
    private UserVehicle userVehicle;

    private String mileage;

    private String source;

    /**
     * The description of the {@code service} of the record.
     */
    private String service;

    /**
     * The {@code date} of the record.
     */
    private Date date;

    /**
     * A technical detail of what was done to the car.
     */
    private String comment;

    public Record() {
        this.userVehicle = null;
    }

    /**
     * @param userVehicle
     * @param mileage
     * @param source
     * @param service
     * @param date
     * @param comment
     */
    public Record(UserVehicle userVehicle, String mileage, String source,
            String service, Date date, String comment) {
        super();
        this.userVehicle = userVehicle;
        this.mileage = mileage;
        this.source = source;
        this.service = service;
        this.date = date;
        this.comment = comment;
    }

    /**
     * @return the userVehicle
     */
    public UserVehicle getUserVehicle() {
        return userVehicle;
    }

    /**
     * @param userVehicle the userVehicle to set
     */
    public void setUserVehicle(UserVehicle userVehicle) {
        this.userVehicle = userVehicle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
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
                .append(this.userVehicle, rhs.userVehicle)
                .append(this.mileage, rhs.mileage)
                .append(this.source, rhs.source)
                .append(this.service, rhs.service)
                .append(this.date, rhs.date)
                .append(this.comment, rhs.comment).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.userVehicle)
                .append(this.mileage)
                .append(this.source)
                .append(this.service)
                .append(this.date)
                .append(this.comment).hashCode();
    }
}
