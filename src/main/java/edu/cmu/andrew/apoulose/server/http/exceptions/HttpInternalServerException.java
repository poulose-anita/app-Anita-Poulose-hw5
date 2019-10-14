package edu.cmu.andrew.apoulose.server.http.exceptions;

import edu.cmu.andrew.apoulose.server.exceptions.AppInternalServerException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.WebApplicationException;

/**
 * Create 500
 */
public class HttpInternalServerException extends WebApplicationException {
    public HttpInternalServerException(int errorCode, String errorMessage) {
        super(Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorModel(
                Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                Status.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                errorCode,
                errorMessage)
        ).type("application/json").build());
    }

    public HttpInternalServerException(int errorCode) {
        super(Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorModel(
                Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                Status.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                errorCode)
        ).type("application/json").build());
    }
    public HttpInternalServerException(AppInternalServerException e) {
        super(Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorModel(
                Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                Status.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getErrorCode(),
                e.getErrorMessage())
        ).type("application/json").build());
    }
}