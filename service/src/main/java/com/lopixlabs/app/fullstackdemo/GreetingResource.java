package com.lopixlabs.app.fullstackdemo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @GET
    public Hello hello() {
        return Hello.of("hello from quarkus!!!");
    }
}
