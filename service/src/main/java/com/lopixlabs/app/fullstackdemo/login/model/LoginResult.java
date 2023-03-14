package com.lopixlabs.app.fullstackdemo.login.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor(staticName = "of")
@Jacksonized
public class LoginResult {
    String token;
}
