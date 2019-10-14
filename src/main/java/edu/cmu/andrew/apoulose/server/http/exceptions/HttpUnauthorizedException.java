package edu.cmu.andrew.apoulose.server.http.exceptions;

import edu.cmu.andrew.apoulose.server.exceptions.AppUnauthorizedException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


public class HttpUnauthorizedException extends WebApplicationException {

    public HttpUnauthorizedException(int errorCode, String errorMessage) {
        super(Response.status(Status.UNAUTHORIZED).entity(new ErrorModel(
                Status.UNAUTHORIZED.getStatusCode(),
                Status.UNAUTHORIZED.getReasonPhrase(),
                errorCode,
                errorMessage)
        ).type("application/json").build());
    }

    public HttpUnauthorizedException(int errorCode){
        super(Response.status(Status.UNAUTHORIZED).entity(new ErrorModel(
                Status.UNAUTHORIZED.getStatusCode(),
                Status.UNAUTHORIZED.getReasonPhrase(),
                errorCode)
        ).type("application/json").build());
    }

    public HttpUnauthorizedException(AppUnauthorizedException e) {
        super(Response.status(Status.UNAUTHORIZED).entity(new ErrorModel(
                Status.UNAUTHORIZED.getStatusCode(),
                Status.UNAUTHORIZED.getReasonPhrase(),
                e.getErrorCode(),
                e.getMessage())
        ).type("application/json").build());
    }
}