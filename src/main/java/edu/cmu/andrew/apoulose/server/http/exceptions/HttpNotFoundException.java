package edu.cmu.andrew.apoulose.server.http.exceptions;

import edu.cmu.andrew.apoulose.server.exceptions.AppNotFoundException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Create 500
 */
public class HttpNotFoundException extends WebApplicationException {
    public HttpNotFoundException(int errorCode, String errorMessage) {
        super(Response.status(Status.NOT_FOUND).entity(new ErrorModel(
                Status.NOT_FOUND.getStatusCode(),
                Status.NOT_FOUND.getReasonPhrase(),
                errorCode,
                errorMessage)
        ).type("application/json").build());
    }

    public HttpNotFoundException(int errorCode) {
        super(Response.status(Status.NOT_FOUND).entity(new ErrorModel(
                Status.NOT_FOUND.getStatusCode(),
                Status.NOT_FOUND.getReasonPhrase(),
                errorCode)
        ).type("application/json").build());
    }
    public HttpNotFoundException(AppNotFoundException e) {
        super(Response.status(Status.NOT_FOUND).entity(new ErrorModel(
                Status.NOT_FOUND.getStatusCode(),
                Status.NOT_FOUND.getReasonPhrase(),
                e.getErrorCode(),
                e.getErrorMessage())
        ).type("application/json").build());
    }

}

