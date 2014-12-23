package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Could not retrieve Question from CDW Service")
public class QBankApiException extends Exception implements Serializable {
	private static final long serialVersionUID = 8301639546218550988L;

    public QBankApiException(final String message) {
        super(message);
    }
}