package com.lopixlabs.app.fullstackdemo.hello;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {
    @Inject
    JsonWebToken jwt;

    @GET
    @Path("permit-all")
    @PermitAll
    public Hello hello(@Context SecurityContext ctx) {
        return Hello.of(getResponseString(ctx));
    }
    @GET
    @Path("roles-allowed")
    @RolesAllowed({ "user", "admin" })
    public Hello helloRolesAllowed(@Context SecurityContext ctx) {
        return Hello.of(getResponseString(ctx));
    }


    private String getResponseString(SecurityContext ctx) {
        String name;
        if (ctx.getUserPrincipal() == null) {
            name = "anonymous";
        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
        } else {
            name = ctx.getUserPrincipal().getName();
        }
        return String.format("hello + %s,"
                + " isHttps: %s,"
                + " authScheme: %s,"
                + " hasJWT: %s",
            name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt());
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }

}
