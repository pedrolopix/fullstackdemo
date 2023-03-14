package com.lopixlabs.app.fullstackdemo.login;

import com.lopixlabs.app.fullstackdemo.login.model.Login;
import com.lopixlabs.app.fullstackdemo.login.model.LoginResult;
import com.lopixlabs.app.fullstackdemo.security.TokenService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LoginService {
    private final TokenService tokenService;

    @Inject
    public LoginService(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public LoginResult login(final Login login) {
        //final String authentication = new String(Base64.decode(authorization)); //user:passs

        final String token = tokenService.generate("teste@test.com", login.getUsername());
        return LoginResult.of(token);
    }

}
