package com.lopixlabs.app.fullstackdemo.security;

import javax.enterprise.context.ApplicationScoped;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Getter
public class TokenSettings {
    public static final String EXPIRE_24HOURS = "86400";
    private final String issuer;
    private final int expiresSeconds;

    public TokenSettings(
        @ConfigProperty(name = "mp.jwt.verify.issuer") final String issuer,
        @ConfigProperty(name = "jwt.expires", defaultValue = EXPIRE_24HOURS) final int expiresSeconds
    ) {
        this.issuer = issuer;
        this.expiresSeconds = expiresSeconds;
    }
}
