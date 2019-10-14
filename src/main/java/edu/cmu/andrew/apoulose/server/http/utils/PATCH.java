package edu.cmu.andrew.apoulose.server.http.utils;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.*;

/**
 * Indicates that the annotated method responds to HTTP PATCH requests.
 *
 * @author Jahara P
 * @see HttpMethod
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod("PATCH")
@Documented
public @interface PATCH {
}


