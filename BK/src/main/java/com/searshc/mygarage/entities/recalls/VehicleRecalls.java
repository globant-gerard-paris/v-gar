package com.searshc.mygarage.entities.recalls;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.searshc.mygarage.util.CustomDateSerializer;

public class VehicleRecalls {

    private DateTime reportReceivedDate;

    private DateTimeZone timezone;

    private String component;

    private String summary;

    private String consequence;

    private String remedy;

    private String notes;

    private int modelYear;

    private String make;

    private String model;

    public VehicleRecalls() {
        this.reportReceivedDate = new DateTime();
        this.timezone = DateTimeZone.forOffsetHours(0);
        this.component = "";
        this.summary = "";
        this.consequence = "";
        this.remedy = "";
        this.notes = "";
        this.modelYear = 0;
    }

    /**
     * @param manufacturer
     * @param nHTSACampaignNumber
     * @param reportReceivedDate
     * @param component
     * @param summary
     * @param consequence
     * @param remedy
     * @param notes
     * @param modelYear
     * @param make
     * @param model
     */
    public VehicleRecalls(final DateTime reportReceivedDate, final DateTimeZone timezone, final String component,
            final String summary, final String consequence, final String remedy,
            final String notes, final int modelYear, final String make, final String model) {

        this.reportReceivedDate = Validate.notNull(reportReceivedDate, "The Report Received Date cannot be null");
        this.timezone = Validate.notNull(timezone, "The TimeZone cannot be null");
        this.component = Validate.notNull(component, "The Component cannot be null");
        this.summary = Validate.notNull(summary, "The Summary cannot be null");
        this.consequence = consequence;
        this.remedy = remedy;
        this.notes = notes;
        Validate.isTrue(modelYear >= 0, "The Model Year cannot be lower than 0");
        this.modelYear = modelYear;
        this.make = Validate.notNull(make, "The Make cannot be null");
        this.model = Validate.notNull(model, "The Model cannot be null");
    }

    /**
     * @return the reportReceivedDate
     */
    @JsonSerialize(using = CustomDateSerializer.class)
    public DateTime getReportReceivedDate() {

        return reportReceivedDate;
    }

    /**
     * @param reportReceivedDate the reportReceivedDate to set
     */
    public void setReportReceivedDate(DateTime reportReceivedDate) {

        this.reportReceivedDate = Validate.notNull(reportReceivedDate, "The Report Received Date cannot be null");
    }

    /**
     * @return the timezone
     */
    public DateTimeZone getTimezone() {

        return timezone;
    }

    /**
     * @param timezone the timezone to set
     */
    public void setTimezone(DateTimeZone timezone) {

        this.timezone = timezone;
    }

    /**
     * @return the component
     */
    public String getComponent() {

        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(String component) {

        this.component = Validate.notNull(component, "The Component cannot be null");
    }

    /**
     * @return the summary
     */
    public String getSummary() {

        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {

        this.summary = Validate.notNull(summary, "The Summary cannot be null");
    }

    /**
     * @return the consequence
     */
    public String getConsequence() {

        return consequence;
    }

    /**
     * @param consequence the consequence to set
     */
    public void setConsequence(String consequence) {

        this.consequence = consequence;
    }

    /**
     * @return the remedy
     */
    public String getRemedy() {

        return remedy;
    }

    /**
     * @param remedy the remedy to set
     */
    public void setRemedy(String remedy) {

        this.remedy = remedy;
    }

    /**
     * @return the notes
     */
    public String getNotes() {

        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {

        this.notes = notes;
    }

    /**
     * @return the modelYear
     */
    public int getModelYear() {

        return modelYear;
    }

    /**
     * @param modelYear the modelYear to set
     */
    public void setModelYear(int modelYear) {

        Validate.isTrue(modelYear >= 0, "The Model Year cannot be lower than 0");
        this.modelYear = modelYear;
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

        this.make = Validate.notNull(make, "The Make cannot be null");
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

        this.model = Validate.notNull(model, "The Model cannot be null");
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.reportReceivedDate)
                .append(this.component)
                .append(this.summary)
                .append(this.consequence)
                .append(this.remedy)
                .append(this.remedy)
                .append(this.notes)
                .append(this.modelYear)
                .append(this.make)
                .append(this.model).toHashCode();
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        VehicleRecalls rhs = (VehicleRecalls) obj;
        return new EqualsBuilder()
                .append(this.reportReceivedDate, rhs.reportReceivedDate)
                .append(this.component, rhs.component)
                .append(this.summary, rhs.summary)
                .append(this.consequence, rhs.consequence)
                .append(this.remedy, rhs.remedy)
                .append(this.notes, rhs.notes)
                .append(this.modelYear, rhs.modelYear)
                .append(this.make, rhs.make)
                .append(this.model, rhs.model).isEquals();
    }

}
