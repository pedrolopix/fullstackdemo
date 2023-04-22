package com.lopixlabs.app.fullstackdemo.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor
@Builder
@Jacksonized
public class Login {
    String username;
    String password;
}
