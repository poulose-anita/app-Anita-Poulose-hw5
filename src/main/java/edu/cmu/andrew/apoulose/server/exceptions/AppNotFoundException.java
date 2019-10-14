package edu.cmu.andrew.apoulose.server.exceptions;

public class AppNotFoundException extends AppException {
    public AppNotFoundException(int errorCode, String errorMessage) {
        super(NOT_FOUND_EXCEPTION, errorCode, errorMessage);
    }

    public AppNotFoundException(int errorCode) {
        super(NOT_FOUND_EXCEPTION, errorCode);
    }
}
