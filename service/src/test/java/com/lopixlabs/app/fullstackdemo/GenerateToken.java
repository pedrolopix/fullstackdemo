package com.lopixlabs.app.fullstackdemo;

import com.lopixlabs.app.fullstackdemo.users.UserRole;
import io.smallrye.jwt.build.Jwt;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import org.eclipse.microprofile.jwt.Claims;

public class GenerateToken {
    /**
     * Generate JWT token
     */
    public static void main(String[] args) {
        String token =
            Jwt.issuer("https://localhost.com/issuer")
                .upn("jdoe@temp.io")
                .groups(new HashSet<>(Arrays.asList(UserRole.ADMIN.name().toLowerCase(Locale.ROOT))))
                .claim(Claims.birthdate.name(), "1973-06-16")
                .sign();
        System.out.println(token);
    }
}
