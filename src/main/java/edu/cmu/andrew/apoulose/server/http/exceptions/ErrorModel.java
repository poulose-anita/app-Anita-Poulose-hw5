package edu.cmu.andrew.apoulose.server.http.exceptions;


public class ErrorModel {

    private int httpStatusCode;
    private String httpStatusMessage;
    private int errorCode;
    private String errorMessage;

    public ErrorModel(int hsc, String hsm, int ec, String em) {
        this.httpStatusCode=hsc;
        this.httpStatusMessage=hsm;
        this.errorCode=ec;
        this.errorMessage = em;
    }
    public ErrorModel(int hsc, String hsm, int ec) {
        this.httpStatusCode=hsc;
        this.httpStatusMessage=hsm;
        this.errorCode=ec;
        this.errorMessage = "Some message";
    }

    public int getHttpStatusCode() { return httpStatusCode; }
    public String getHttpStatusMessage() { return httpStatusMessage; }
    public int getErrorCode() { return errorCode; }
    public String getErrorMessage() {return errorMessage; }
}
