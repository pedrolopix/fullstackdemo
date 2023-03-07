package com.lopixlabs.app.fullstackdemo.security;

import io.quarkus.runtime.annotations.ConfigItem;
import javax.enterprise.context.ApplicationScoped;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Getter
public class TokenSettings {
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    private String issuer;

    @ConfigProperty(name = "jwt.expires", defaultValue = "3000")
    private int expires;
}
