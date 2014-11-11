package com.searshc.mygarage.apis.nhtsa.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NHTSARecalls {
	
	@JsonProperty(value = "Count")
	private int count;
	
	@JsonProperty(value = "Message")
	private String message;
	
	@JsonProperty(value = "Results")
	private List<NHTSARecallDetails> nhtsaRecalls;
	
	public NHTSARecalls() {
	}

	/**
	 * @param count
	 * @param message
	 * @param recalls
	 */
	public NHTSARecalls(int count, String message, List<NHTSARecallDetails> recalls) {
		this.count = count;
		this.message = message;
		this.nhtsaRecalls = recalls;
	}

	
	/**
	 * @return the count
	 */
	public int getCount() {
	
		return count;
	}

	
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
	
		this.count = count;
	}

	
	/**
	 * @return the message
	 */
	public String getMessage() {
	
		return message;
	}

	
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
	
		this.message = message;
	}

	
	/**
	 * @return the nhtsaRecalls
	 */
	public List<NHTSARecallDetails> getNhtsaRecalls() {
	
		return nhtsaRecalls;
	}

	
	public void addNhtsaRecall(NHTSARecallDetails nhtsaRecall) {
		if(this.nhtsaRecalls == null) {
			this.nhtsaRecalls = new ArrayList<NHTSARecallDetails>();
		}
		this.nhtsaRecalls.add(nhtsaRecall);
	}
	
	/**
	 * @param nhtsaRecalls the nhtsaRecalls to set
	 */
	public void setNhtsaRecalls(List<NHTSARecallDetails> nhtsaRecalls) {
	
		this.nhtsaRecalls = nhtsaRecalls;
	}
	
}
