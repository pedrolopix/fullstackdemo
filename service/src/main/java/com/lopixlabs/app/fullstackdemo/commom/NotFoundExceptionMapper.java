package com.lopixlabs.app.fullstackdemo.commom;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND;

import java.io.InputStream;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

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
