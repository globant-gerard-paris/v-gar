package com.searshc.mygarage.apis.nhtsa.response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NHTSARecallDetails {
	
	@JsonProperty(value = "Manufacturer")
	private String manufacturer;
	
	@JsonProperty(value = "NHTSACampaignNumber")
	private String NHTSACampaignNumber;
	
	@JsonProperty(value = "ReportReceivedDate")
	private String reportReceivedDate;
	
	@JsonProperty(value = "Component")
	private String component;
	
	@JsonProperty(value = "Summary")
	private String summary;
	
	@JsonProperty(value = "Conequence")
	private String consequence;
	
	@JsonProperty(value = "Remedy")
	private String remedy;
	
	@JsonProperty(value = "Notes")
	private String notes;
	
	@JsonProperty(value = "ModelYear")
	private String modelYear;
	
	@JsonProperty(value = "Make")
	private String make;
	
	@JsonProperty(value = "Model")
	private String model;

	
	
	public NHTSARecallDetails() {
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
	public NHTSARecallDetails(String manufacturer, String nHTSACampaignNumber, String reportReceivedDate, String component,
			String summary, String consequence, String remedy, String notes, String modelYear, String make, String model) {
	
		this.manufacturer = manufacturer;
		this.NHTSACampaignNumber = nHTSACampaignNumber;
		this.reportReceivedDate = reportReceivedDate;
		this.component = component;
		this.summary = summary;
		this.consequence = consequence;
		this.remedy = remedy;
		this.notes = notes;
		this.modelYear = modelYear;
		this.make = make;
		this.model = model;
	}


	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
	
		return manufacturer;
	}

	
	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
	
		this.manufacturer = manufacturer;
	}

	
	/**
	 * @return the nHTSACampaignNumber
	 */
	public String getNHTSACampaignNumber() {
	
		return NHTSACampaignNumber;
	}

	
	/**
	 * @param nHTSACampaignNumber the nHTSACampaignNumber to set
	 */
	public void setNHTSACampaignNumber(String nHTSACampaignNumber) {
	
		NHTSACampaignNumber = nHTSACampaignNumber;
	}

	
	/**
	 * @return the reportReceivedDate
	 */
	public String getReportReceivedDate() {
	
		return reportReceivedDate;
	}

	
	/**
	 * @param reportReceivedDate the reportReceivedDate to set
	 */
	public void setReportReceivedDate(String reportReceivedDate) {
	
		this.reportReceivedDate = reportReceivedDate;
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
	
		this.component = component;
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
	
		this.summary = summary;
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
	public String getModelYear() {
	
		return modelYear;
	}

	
	/**
	 * @param modelYear the modelYear to set
	 */
	public void setModelYear(String modelYear) {
	
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
	
		this.make = make;
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
	
		this.model = model;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.manufacturer)
			.append(this.NHTSACampaignNumber)
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
	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NHTSARecallDetails rhs = (NHTSARecallDetails) obj;
		return new EqualsBuilder()
			.append(this.manufacturer, rhs.manufacturer)
			.append(this.NHTSACampaignNumber, rhs.NHTSACampaignNumber)
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
	
	public String toString() {
		return new StringBuilder()
			.append("Manufacturer: ").append(this.manufacturer).append(" - ")
			.append("NHTSACampaignNumber: ").append(this.NHTSACampaignNumber).append(" - ")
			.append("ReportReceivedDate: ").append(this.reportReceivedDate).append(" - ")
			.append("Component: ").append(this.component).append(" - ")
			.append("Summary: ").append(this.summary).append(" - ")
			.append("Consequence: ").append(this.consequence).append(" - ")
			.append("Remedy: ").append(this.remedy).append(" - ")
			.append("Notes: ").append(this.notes).append(" - ")
			.append("ModelYear: ").append(this.modelYear).append(" - ")
			.append("Make: ").append(this.make).append(" - ")
			.append("Model: ").append(this.model).toString();
			
	}
	
}
