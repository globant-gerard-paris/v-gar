package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Could not retrieve vehicles from NCDB")
public class NCDBApiException extends Exception implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 680348496818195966L;

    public NCDBApiException(final String message) {
        super(message);
    }
}
