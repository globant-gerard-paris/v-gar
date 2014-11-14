package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends Exception implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7459111125095844164L;

	public UserNotFoundException(final String message) {
		super(message);
	}
}
