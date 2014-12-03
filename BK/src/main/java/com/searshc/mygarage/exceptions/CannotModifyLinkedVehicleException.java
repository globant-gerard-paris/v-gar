package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Cannot modify a FamilyVehicle linked from NCDB")
public class CannotModifyLinkedVehicleException extends RuntimeException implements Serializable {

	/**
	 * The Serial Version UID
	 */
	private static final long serialVersionUID = -92782883390026644L;

	public CannotModifyLinkedVehicleException(final String message) {
		super(message);
	}
}
