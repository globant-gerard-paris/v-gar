package com.searshc.mygarage.apis.trends.response;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendReader {

	@JsonProperty(value = "ta_id")
	private String taId;
	
	@JsonProperty(value = "topic_id")
	private String topicId;
	
	@JsonProperty(value = "article_id")
	private String articleId;
	
	@JsonProperty(value = "title")
	private String title;
	
	@JsonProperty(value = "summary")
	private String summary;
	
	@JsonProperty(value = "publisherName")
	private String publisherName;
	
	@JsonProperty(value = "source_url")
	private String sourceUrl;
	
	@JsonProperty(value = "image_url")
	private String imageUrl;
	
	@JsonProperty(value = "publish_date")
	private String publishDate;
	
	@JsonProperty(value = "topic")
	private String topic;
	
	@JsonProperty(value = "category")
	private String category;
	
	@JsonProperty(value = "subCategory")
	private String subCategory;
	
	@JsonProperty(value = "facebook_shares")
	private String facebookShares;
	
	@JsonProperty(value = "shopyourway_shares")
	private String shopyourwayShares;
	
	@JsonProperty(value = "twitter_shares")
	private String twitterShares;
	
	@JsonProperty(value = "email_shares")
	private String emailShares;
	
	@JsonProperty(value = "numberOfLikes")
	private String numberOfLikes;	

	
	
	public TrendReader() {
	}
	
	
	/**
	 * @param taId
	 * @param topicId
	 * @param articleId
	 * @param title
	 * @param summary
	 * @param publisherName
	 * @param sourceUrl
	 * @param imageUrl
	 * @param publishDate
	 * @param topic
	 * @param category
	 * @param subCategory
	 * @param facebookShares
	 * @param shopyourwayShares
	 * @param twitterShares
	 * @param emailShares
	 * @param numberOfLikes
	 */
	public TrendReader(String taId, String topicId, String articleId, String title, String summary, String publisherName,
			String sourceUrl, String imageUrl, String publishDate, String topic, String category, String subCategory, 
			String facebookShares, String shopyourwayShares,String twitterShares,String emailShares,String numberOfLikes) {
	
		this.taId = taId;
		this.topicId = topicId;
		this.articleId = articleId;
		this.title = title;
		this.summary = summary;
		this.publisherName = publisherName;
		this.sourceUrl = sourceUrl;
		this.imageUrl = imageUrl;
		this.publishDate = publishDate;
		this.topic = topic;
		this.category = category;
		this.subCategory = subCategory;
		this.facebookShares = facebookShares;
		this.shopyourwayShares = shopyourwayShares;
		this.twitterShares = twitterShares;
		this.emailShares = emailShares;
		this.numberOfLikes = numberOfLikes;
	}


	public String getTaId() {
		return taId;
	}


	public void setTaId(String taId) {
		this.taId = taId;
	}


	public String getTopicId() {
		return topicId;
	}


	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}


	public String getArticleId() {
		return articleId;
	}


	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public String getPublisherName() {
		return publisherName;
	}


	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}


	public String getSourceUrl() {
		return sourceUrl;
	}


	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getPublishDate() {
		return publishDate;
	}


	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getSubCategory() {
		return subCategory;
	}


	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}


	public String getFacebookShares() {
		return facebookShares;
	}


	public void setFacebookShares(String facebookShares) {
		this.facebookShares = facebookShares;
	}


	public String getShopyourwayShares() {
		return shopyourwayShares;
	}


	public void setShopyourwayShares(String shopyourwayShares) {
		this.shopyourwayShares = shopyourwayShares;
	}


	public String getTwitterShares() {
		return twitterShares;
	}


	public void setTwitterShares(String twitterShares) {
		this.twitterShares = twitterShares;
	}


	public String getEmailShares() {
		return emailShares;
	}


	public void setEmailShares(String emailShares) {
		this.emailShares = emailShares;
	}


	public String getNumberOfLikes() {
		return numberOfLikes;
	}


	public void setNumberOfLikes(String numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.taId)
			.append(this.topicId)
			.append(this.articleId)
			.append(this.title)
			.append(this.summary)
			.append(this.publisherName)
			.append(this.sourceUrl)
			.append(this.imageUrl)
			.append(this.publishDate)
			.append(this.topic)
			.append(this.category)
			.append(this.subCategory)
			.append(this.facebookShares)
			.append(this.shopyourwayShares)
			.append(this.twitterShares)
			.append(this.emailShares)
			.append(this.numberOfLikes).toHashCode();
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
		TrendReader rhs = (TrendReader) obj;
		return new EqualsBuilder()
			.append(this.taId, rhs.taId)
			.append(this.topicId, rhs.topicId)
			.append(this.articleId, rhs.articleId)
			.append(this.title, rhs.title)
			.append(this.summary, rhs.summary)
			.append(this.publisherName, rhs.publisherName)
			.append(this.sourceUrl, rhs.sourceUrl)
			.append(this.imageUrl, rhs.imageUrl)
			.append(this.publishDate, rhs.publishDate)
			.append(this.topic, rhs.topic)
			.append(this.category, rhs.category)
			.append(this.subCategory, rhs.subCategory)
			.append(this.facebookShares, rhs.facebookShares)
			.append(this.shopyourwayShares, rhs.shopyourwayShares)
			.append(this.twitterShares, rhs.twitterShares)
			.append(this.emailShares, rhs.emailShares)
			.append(this.numberOfLikes, rhs.numberOfLikes).isEquals();	
	}
	
	public String toString() {
		return new StringBuilder()
			.append("taId: ").append(this.taId).append(" - ")
			.append("topicId: ").append(this.topicId).append(" - ")
			.append("articleId: ").append(this.articleId).append(" - ")
			.append("title: ").append(this.title).append(" - ")
			.append("summary: ").append(this.summary).append(" - ")
			.append("publisherName: ").append(this.publisherName).append(" - ")
			.append("sourceUrl: ").append(this.sourceUrl).append(" - ")
			.append("imageUrl: ").append(this.imageUrl).append(" - ")
			.append("publishDate: ").append(this.publishDate).append(" - ")
			.append("topic: ").append(this.topic).append(" - ")
			.append("category: ").append(this.category).append(" - ")
			.append("subCategory: ").append(this.subCategory).append(" - ")
			.append("facebookShares: ").append(this.facebookShares).append(" - ")
			.append("shopyourwayShares: ").append(this.shopyourwayShares).append(" - ")
			.append("twitterShares: ").append(this.twitterShares).append(" - ")
			.append("emailShares: ").append(this.emailShares).append(" - ")
			.append("numberOfLikes: ").append(this.numberOfLikes).toString();
	}
	
}
