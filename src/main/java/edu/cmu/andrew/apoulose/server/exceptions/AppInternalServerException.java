package edu.cmu.andrew.apoulose.server.exceptions;

public class AppInternalServerException extends AppException {
    public AppInternalServerException(int errorCode, String errorMessage) {
        super(INTERNAL_SERVER_EXCEPTION, errorCode, errorMessage);
    }

    public AppInternalServerException(int errorCode) {
        super(INTERNAL_SERVER_EXCEPTION, errorCode);
    }
}
