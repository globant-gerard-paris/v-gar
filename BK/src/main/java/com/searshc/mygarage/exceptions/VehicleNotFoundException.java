package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Vehicle not found")
public class VehicleNotFoundException extends RuntimeException implements Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = -9079971556072239181L;

	public VehicleNotFoundException(final String message) {
		super(message);
	}
}
