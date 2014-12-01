package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "SuggestedService not found")
public class SuggestedServiceNotFoundException extends RuntimeException implements Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 368843884528973485L;

	/**
	 * @param message
	 */
	public SuggestedServiceNotFoundException(String message) {
		super(message);
	}

}
