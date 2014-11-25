package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jero
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "An error has occurred. We recorded the error in our logs for further investigation. Please try again and if it persists contact Technical Support.")
public class VirtualGarageServiceException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -198362455454212091L;

	public VirtualGarageServiceException(final String message) {
		super(message);
	}
	public VirtualGarageServiceException(final Throwable cause) {
		super(cause);
	}
}
