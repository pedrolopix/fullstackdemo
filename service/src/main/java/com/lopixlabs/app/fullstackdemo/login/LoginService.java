package com.lopixlabs.app.fullstackdemo.login;

import com.lopixlabs.app.fullstackdemo.login.model.Login;
import com.lopixlabs.app.fullstackdemo.login.model.LoginResult;
import com.lopixlabs.app.fullstackdemo.security.TokenService;
import java.util.Arrays;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jose4j.base64url.Base64;

@ApplicationScoped
public class LoginService {
    private final TokenService tokenService;

    @Inject
    public LoginService(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public LoginResult login(final Login login) {
        final String authentication = new String(Base64.decode("dXNlcjpwYXNz")); //user:passs

        final String token = tokenService.generate("teste@test.com", "user");
        return LoginResult.of(token);
    }

}
