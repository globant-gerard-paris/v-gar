package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Could not retrieve information from SYW services.")
public class SYWServiceException extends RuntimeException implements Serializable {
	
	private static final long serialVersionUID = -198362455454212091L;

	public SYWServiceException(final String message) {
		super(message);
	}
}
