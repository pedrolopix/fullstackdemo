package com.lopixlabs.app.fullstackdemo.auth.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor(staticName = "of")
@Jacksonized
public class AuthResult {
    String token;
    String refreshToken;
}
