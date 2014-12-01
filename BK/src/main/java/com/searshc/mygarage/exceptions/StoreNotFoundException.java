package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Store not found")
public class StoreNotFoundException extends RuntimeException implements Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 5292565335226980208L;

	public StoreNotFoundException(final String message) {
		super(message);
	}
}
