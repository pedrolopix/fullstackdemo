package com.lopixlabs.app.fullstackdemo.security;

import com.lopixlabs.app.fullstackdemo.users.UserRole;
import io.smallrye.jwt.build.Jwt;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
@Slf4j
public class TokenService {
    private final TokenSettings tokenSettings;

    public TokenService(final TokenSettings tokenSettings) {
        this.tokenSettings = tokenSettings;
    }

    public String generate(String email, String username) {
        try {
            return Jwt.issuer(tokenSettings.getIssuer())
                .upn(email)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(tokenSettings.getExpiresSeconds()))
                .preferredUserName(username)
                .groups(new HashSet<>(Arrays.asList(UserRole.USER.name().toLowerCase(Locale.ROOT))))
                .sign();
        } catch (Exception e) {
            log.error("Error generate token: " + e.getMessage(), e);
            throw new TokenException("Error generate token: " + e.getMessage(), e);
        }
    }

    /**
     * @return the current time in seconds since epoch
     */
    public static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }

    public String renew(final JsonWebToken jwt) {
        return Jwt.issuer(tokenSettings.getIssuer())
            .upn(jwt.getClaim("upn"))
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(tokenSettings.getExpiresSeconds()))
            .preferredUserName(jwt.getName())
            .groups(jwt.getGroups())
            .sign();
    }
}
