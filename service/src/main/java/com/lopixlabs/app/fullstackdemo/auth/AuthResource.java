package com.lopixlabs.app.fullstackdemo.auth;

import static com.lopixlabs.app.fullstackdemo.security.Roles.REFRESH;

import com.lopixlabs.app.fullstackdemo.auth.model.Login;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import com.lopixlabs.app.fullstackdemo.auth.model.AuthResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
