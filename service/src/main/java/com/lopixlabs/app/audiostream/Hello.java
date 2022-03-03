package com.lopixlabs.app.fullstackdemo;

import lombok.Value;

@Value(staticConstructor = "of")
public class Hello {
    String value;
}
