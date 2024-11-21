package edu.academik.awjservice.rest.cors;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    private static final List<String> list = Arrays.asList(
            "http://localhost:8081",
            "http://localhost:8082");

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        var origin = requestContext.getHeaderString("Origin");

        if (list.contains(origin)) {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", origin);
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
            responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        }
    }

}
