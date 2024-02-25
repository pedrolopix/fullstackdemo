package com.lopixlabs.app.fullstackdemo.commom;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND;

import java.io.InputStream;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    @Produces(MediaType.TEXT_HTML)
    public Response toResponse(NotFoundException exception) {
        InputStream resource = ClassLoader.getSystemResourceAsStream("META-INF/resources/index.html");
        return null == resource
            ? Response.status(NOT_FOUND).build()
            : Response.ok().entity(resource).build();
    }
}
