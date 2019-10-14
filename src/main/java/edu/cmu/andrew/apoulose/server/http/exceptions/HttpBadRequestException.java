package edu.cmu.andrew.apoulose.server.http.exceptions;

        import edu.cmu.andrew.apoulose.server.exceptions.AppBadRequestException;

        import javax.ws.rs.WebApplicationException;
        import javax.ws.rs.core.Response;
        import javax.ws.rs.core.Response.Status;


public class HttpBadRequestException extends WebApplicationException {

    public HttpBadRequestException(int errorCode, String errorMessage) {
        super(Response.status(Status.BAD_REQUEST)
                .entity(new ErrorModel(
                        Status.BAD_REQUEST.getStatusCode(),
                        Status.BAD_REQUEST.getReasonPhrase(),
                        errorCode,
                        errorMessage)
                ).type("application/json").build());
    }

    public HttpBadRequestException(int errorCode) {
        super(Response.status(Status.BAD_REQUEST)
                .entity(new ErrorModel(
                        Status.BAD_REQUEST.getStatusCode(),
                        Status.BAD_REQUEST.getReasonPhrase(),
                        errorCode)
                ).type("application/json").build());
    }

    public HttpBadRequestException(AppBadRequestException e) {
        super(Response.status(Status.BAD_REQUEST)
                .entity(new ErrorModel(
                        Status.BAD_REQUEST.getStatusCode(),
                        Status.BAD_REQUEST.getReasonPhrase(),
                        e.getErrorCode(),
                        e.getErrorMessage())
                ).type("application/json").build());
    }

}