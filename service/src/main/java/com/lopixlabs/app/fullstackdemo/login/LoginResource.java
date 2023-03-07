package com.lopixlabs.app.fullstackdemo.login;

import com.lopixlabs.app.fullstackdemo.login.model.Login;
import com.lopixlabs.app.fullstackdemo.login.model.LoginResult;
import javax.annotation.security.PermitAll;
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
@Path("api")
public class LoginResource {
    private final LoginService loginService;
    private final JsonWebToken jwt;
    @Inject
    public LoginResource(final LoginService loginService, final JsonWebToken jwt) {
        this.loginService = loginService;
        this.jwt = jwt;
    }

    @POST
    @Path("login")
    @PermitAll
    public LoginResult hello(final Login login) {
        return loginService.login(login);
    }

    @GET
    @Path("renew")
    public LoginResult hello() {
        return loginService.renew(jwt);
    }

}
