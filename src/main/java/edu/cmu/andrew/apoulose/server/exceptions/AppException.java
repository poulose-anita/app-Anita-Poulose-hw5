package edu.cmu.andrew.apoulose.server.exceptions;

import edu.cmu.andrew.apoulose.server.http.exceptions.HttpBadRequestException;
import edu.cmu.andrew.apoulose.server.http.exceptions.HttpInternalServerException;
import edu.cmu.andrew.apoulose.server.http.exceptions.HttpNotFoundException;
import edu.cmu.andrew.apoulose.server.http.exceptions.HttpUnauthorizedException;

import javax.ws.rs.WebApplicationException;

public class AppException extends Exception{

    public static final int INTERNAL_SERVER_EXCEPTION = 0;
    public static final int NOT_FOUND_EXCEPTION = 1;
    public static final int BAD_REQUEST_EXCEPTION = 2;
    public static final int UNAUTHORIZED_EXCEPTION = 3;

    int type;
    private int errorCode;
    private String errorMessage;

    public AppException(int type, int errorCode, String errorMessage) {
        this.type = type;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public AppException(int type, int errorCode) {
        this.type = type;
        this.errorCode = errorCode;
        this.errorMessage = "Some message";

    }

    public int getType() {
        return type;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public WebApplicationException getHttpException(){
        switch(type) {
            case AppException.NOT_FOUND_EXCEPTION:
                return new HttpNotFoundException((AppNotFoundException)this);

            case AppException.BAD_REQUEST_EXCEPTION:
                return new HttpBadRequestException((AppBadRequestException)this);

            case AppException.UNAUTHORIZED_EXCEPTION:
                return new HttpUnauthorizedException((AppUnauthorizedException)this);

            default:
                return new HttpInternalServerException((AppInternalServerException)this);
        }
    }
}


