package com.lopixlabs.app.fullstackdemo.auth;

import static com.lopixlabs.app.fullstackdemo.users.UserRole.USER;

import com.lopixlabs.app.fullstackdemo.auth.model.Login;
import com.lopixlabs.app.fullstackdemo.auth.model.AuthResult;
import com.lopixlabs.app.fullstackdemo.security.TokenService;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class AuthService {
    private final TokenService tokenService;

    @Inject
    public AuthService(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public AuthResult login(final Login login) {
        //final String authentication = new String(Base64.decode(authorization)); //user:passs

        final String token = tokenService.generate("teste@test.com", List.of(USER.getRoleName()));
        final String refreshToken = tokenService.generateRefreshToken("teste@test.com");
        return AuthResult.of(token, refreshToken);
    }

    public AuthResult refresh(final JsonWebToken jwt) {
        final String token = tokenService.generate("teste@test.com", List.of(USER.getRoleName()));
        final String refreshToken = tokenService.generateRefreshToken("teste@test.com");
        return AuthResult.of(token, refreshToken);
    }
}
