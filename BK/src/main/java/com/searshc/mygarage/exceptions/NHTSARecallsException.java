package com.searshc.mygarage.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Could not retrieve Recalls from NHSTSA")
public class NHTSARecallsException extends RuntimeException implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1983624556433245091L;

    public NHTSARecallsException(final String message) {
        super(message);
    }
}
