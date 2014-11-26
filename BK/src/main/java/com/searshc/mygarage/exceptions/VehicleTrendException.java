package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Could not retrieve Trends from Trends Reader")
public class VehicleTrendException extends Exception implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1983624556433245091L;

	public VehicleTrendException(final String message) {
		super(message);
	}
}
