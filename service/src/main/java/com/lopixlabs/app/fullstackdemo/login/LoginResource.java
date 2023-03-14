package com.lopixlabs.app.fullstackdemo.login;

import com.lopixlabs.app.fullstackdemo.login.model.Login;
import com.lopixlabs.app.fullstackdemo.login.model.LoginResult;
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
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.jboss.resteasy.reactive.RestHeader;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Path("/api")
public class LoginResource {
    private final LoginService loginService;
    private final JsonWebToken jwt;

    @Inject
    public LoginResource(final LoginService loginService, final JsonWebToken jwt) {
        this.loginService = loginService;
        this.jwt = jwt;
    }

    @POST
    @Path("/login")
    @PermitAll
    public LoginResult login(final Login login) {
        return loginService.login(login);
    }

    @GET
    @Path("/me")
    @RolesAllowed("user")
    public LoginResult me() {
        return LoginResult.of(jwt.getRawToken());
    }

}
