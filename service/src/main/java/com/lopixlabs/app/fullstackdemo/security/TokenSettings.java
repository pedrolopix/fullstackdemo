package com.lopixlabs.app.fullstackdemo.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Getter
public class TokenSettings {
    public static final String EXPIRE_5MIN = "300";
    public static final String EXPIRE_24HOURS = "86400";
    private final String issuer;
    private final int expiresSeconds;
    private final int refreshExpiresSeconds;
    @Inject
    public TokenSettings(
        @ConfigProperty(name = "mp.jwt.verify.issuer") final String issuer,
        @ConfigProperty(name = "jwt.expires", defaultValue = EXPIRE_5MIN) final int expiresSeconds,
        @ConfigProperty(name = "jwt.refreshExpires", defaultValue = EXPIRE_24HOURS) final int refreshExpiresSeconds
    ) {
        this.issuer = issuer;
        this.expiresSeconds = expiresSeconds;
        this.refreshExpiresSeconds = refreshExpiresSeconds;
    }
}
