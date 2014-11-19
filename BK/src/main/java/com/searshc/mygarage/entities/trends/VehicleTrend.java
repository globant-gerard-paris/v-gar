package com.searshc.mygarage.entities.trends;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.searshc.mygarage.util.CustomDateSerializer;


public class VehicleTrend {
	
	private String title;
	
	private String summary;
	
	private String imageUrl;
	
	private String sourceUrl;
	
	private String topic;
	
	
	public VehicleTrend() {
		this.title = "";
		this.summary = "";
		this.imageUrl = "";
		this.sourceUrl = "";
		this.topic = "";
	}
	
	/**
	 * @param title
	 * @param summary
	 * @param imageUrl
	 * @param sourceUrl
	 * @param topic
	 */
	public VehicleTrend(final String title, final String summary, 
			final String imageUrl, final String sourceUrl,
			final String topic) {
	
		this.title = Validate.notNull(title, "The Title cannot be null");
		this.summary = Validate.notNull(summary, "The Summary cannot be null");
		this.imageUrl = Validate.notNull(imageUrl, "The Image Url cannot be null");
		this.sourceUrl = Validate.notNull(sourceUrl, "The Source Url cannot be null");
		this.topic = Validate.notNull(topic, "The Topic cannot be null");
	}

	
	/**
	 * @return the title
	 */
	public String getTitle() {
	
		return title;
	}

	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
	
		this.title = Validate.notNull(title, "The Title cannot be null");
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
	 * @return the imageUrl
	 */
	public String getImageUrl() {
	
		return imageUrl;
	}

	
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
	
		this.imageUrl = Validate.notNull(imageUrl, "The Image Url cannot be null");
	}	


	/**
	 * @return the sourceUrl
	 */
	public String getSourceUrl() {
	
		return sourceUrl;
	}

	
	/**
	 * @param sourceUrl the sourceUrl to set
	 */
	public void setSourceUrl(String sourceUrl) {
	
		this.sourceUrl = Validate.notNull(sourceUrl, "The Source Url cannot be null");
	}
	

	/**
	 * @return the topic
	 */
	public String getTopic() {
	
		return topic;
	}

	
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
	
		this.topic = Validate.notNull(topic, "The Topic cannot be null");
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.title)
			.append(this.summary)
			.append(this.imageUrl)
			.append(this.sourceUrl)
			.append(this.topic).toHashCode();
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
		VehicleTrend rhs = (VehicleTrend) obj;
		return new EqualsBuilder()
			.append(this.title, rhs.title)
			.append(this.summary, rhs.summary)
			.append(this.imageUrl, rhs.imageUrl)
			.append(this.sourceUrl, rhs.sourceUrl)
			.append(this.topic, rhs.topic).isEquals();	
	}
	

}
