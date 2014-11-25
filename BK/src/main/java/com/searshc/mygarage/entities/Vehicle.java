package com.searshc.mygarage.entities;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "vehicle")
public class Vehicle extends AbstractEntity implements Serializable {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -4412113151100181178L;
    /**
     * The Vehicle year. Required.
     */
    @Column(name = "year")
    private int year;
    /**
     * The Vehicle Make. Required.
     */
    @Column(name = "make")
    private String make;
    /**
     * The Vehicle Model. Required.
     */
    @Column(name = "model")
    private String model;
    /**
     * The Vehicle Engine.
     */
    @Column(name = "engine")
    private String engine;
    /**
     * The Vehicle Type. TODO: use a enum insted of String.
     * <br>Possible values:
     * <ul><strong>P</strong> for passenger Vehicles</ul>
     * <ul><strong>LT</strong> for Light Duty Vehicles</ul>
     * <ul><strong>MD</strong> for Medium Duty Vehicles</ul>
     */
    @Column(name = "type")
    private String type;

    public Vehicle() {
        super();
        this.year = 0;
        this.make = "";
        this.model = "";
    }

    /**
     * @param year required.
     * @param make required.
     * @param model required.
     * @param engine
     * @param type
     */
    public Vehicle(final int year, final String make, final String model, final String engine, final String type) {
        super();
        isTrue(year >= 0, "The year cannot be lower than 0");
        this.year = year;
        this.make = notNull(make, "The Make cannot be null");
        this.model = notNull(model, "The Model cannot be null");
        this.engine = engine;
        this.type = type;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        isTrue(year >= 0, "The year cannot be lower than 0");
        this.year = year;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = notNull(make, "The Make cannot be null");
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = notNull(model, "The Model cannot be null");
    }

    /**
     * @return the engine
     */
    public String getEngine() {
        return engine;
    }

    /**
     * @param engine the engine to set
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Vehicle rhs = (Vehicle) obj;
        return new EqualsBuilder()
                .append(this.engine, rhs.engine)
                .append(this.year, rhs.year)
                .append(this.make, rhs.make)
                .append(this.model, rhs.model).isEquals();
    }
    
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(this.engine)
        .append(this.year)
        .append(this.make)
        .append(this.model).hashCode();
    }

}
