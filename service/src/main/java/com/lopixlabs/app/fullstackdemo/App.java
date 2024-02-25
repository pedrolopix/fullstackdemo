package com.lopixlabs.app.fullstackdemo;


import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
    tags = {
        @Tag(name = "Demo", description = "Quarkus and svelte demo application"),
    },
    info = @Info(
        title = "Quarkus and svelte demo application",
        version = "1.0.0"
    ),
    security = @SecurityRequirement(name = "basicAuth")
)
//@SecurityScheme(
//    securitySchemeName = "basicAuth", // can be set to anything
//    type = SecuritySchemeType.HTTP,
//    scheme = "basic"
//)
@SecurityScheme(
    type = SecuritySchemeType.APIKEY
)
public class App extends Application {
}
