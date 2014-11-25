package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "FamilyVehicle not found")
public class FamilyVehicleNotFoundException extends Exception implements Serializable {

	/**
	 * The Serial Version UID. 
	 */
	private static final long serialVersionUID = 7674587616960905338L;

	public FamilyVehicleNotFoundException(final String message) {
		super(message);
	}
}
