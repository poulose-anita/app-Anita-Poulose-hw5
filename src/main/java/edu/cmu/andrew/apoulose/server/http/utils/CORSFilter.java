package edu.cmu.andrew.apoulose.server.http.utils;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request,
                       ContainerResponseContext response) throws IOException {
        // *(allow from all servers)
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        // As part of the response to a request, which HTTP headers can be used during the actual request.
        response.getHeaders().add("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization, token");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        // As a part of the response to a request, which HTTP methods can be used during the actual request.
        response.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PATCH, PUT, DELETE, OPTIONS, HEAD");
    }
}
