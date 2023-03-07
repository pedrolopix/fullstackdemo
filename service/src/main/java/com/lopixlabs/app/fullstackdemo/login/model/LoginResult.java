package com.lopixlabs.app.fullstackdemo.login.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class LoginResult {
    String jwt;
}
