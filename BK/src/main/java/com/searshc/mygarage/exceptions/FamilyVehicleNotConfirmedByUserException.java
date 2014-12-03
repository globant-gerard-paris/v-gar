package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The user has not confirmed the FamilyVehicle")
public class FamilyVehicleNotConfirmedByUserException extends RuntimeException implements Serializable {

	/**
	 * The Serial Version UID
	 */
	private static final long serialVersionUID = -4076363323235635642L;

	public FamilyVehicleNotConfirmedByUserException(final String message) {
		super(message);
	}
}
