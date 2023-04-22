package com.lopixlabs.app.fullstackdemo.auth;

import static com.lopixlabs.app.fullstackdemo.security.Roles.REFRESH;

import com.lopixlabs.app.fullstackdemo.auth.model.Login;
import com.lopixlabs.app.fullstackdemo.auth.model.AuthResult;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Path("/api/auth")
public class AuthResource {
    private final AuthService authService;
    private final JsonWebToken jwt;

    @Inject
    public AuthResource(final AuthService authService, final JsonWebToken jwt) {
        this.authService = authService;
        this.jwt = jwt;
    }

    @POST
    @Path("signin")
    @PermitAll
    public AuthResult login(final Login login) {
        return authService.login(login);
    }

    @GET
    @Path("refresh")
    @RolesAllowed(REFRESH)
    public AuthResult refresh() {
        return authService.refresh(jwt);
    }
}
