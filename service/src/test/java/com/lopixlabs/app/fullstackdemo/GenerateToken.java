package com.lopixlabs.app.fullstackdemo;

import io.smallrye.jwt.build.Jwt;
import java.util.Arrays;
import java.util.HashSet;
import org.eclipse.microprofile.jwt.Claims;

public class GenerateToken {
    /**
     * Generate JWT token
     */
    public static void main(String[] args) {
        String token =
            Jwt.issuer("https://localhost.com/issuer")
                .upn("jdoe@temp.io")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.birthdate.name(), "1973-06-16")
                .sign();
        System.out.println(token);
    }
}
