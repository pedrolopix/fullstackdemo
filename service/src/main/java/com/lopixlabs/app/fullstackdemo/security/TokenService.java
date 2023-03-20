package com.lopixlabs.app.fullstackdemo.security;

import static com.lopixlabs.app.fullstackdemo.security.Roles.REFRESH;

import io.smallrye.jwt.build.Jwt;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class TokenService {
    private final TokenSettings tokenSettings;

    // TODO WIP
    public TokenService(final TokenSettings tokenSettings) {
        this.tokenSettings = tokenSettings;
    }

    public String generate(final String email, final List<String> roles) {
        try {
            return Jwt.issuer(tokenSettings.getIssuer())
                .upn(email)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(tokenSettings.getExpiresSeconds()))
                .groups(new HashSet<>(roles))
                .sign();
        } catch (Exception e) {
            log.error("Error generate token: " + e.getMessage(), e);
            throw new TokenException("Error generate token: " + e.getMessage(), e);
        }
    }

    public String generateRefreshToken(String email) {
        return Jwt.issuer(tokenSettings.getIssuer())
            .upn(email)
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusSeconds(tokenSettings.getRefreshExpiresSeconds()))
            .groups(new HashSet<>(List.of(REFRESH)))
            .sign();
    }
}
