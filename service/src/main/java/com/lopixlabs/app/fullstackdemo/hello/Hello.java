package com.lopixlabs.app.fullstackdemo.hello;

import lombok.Value;

@Value(staticConstructor = "of")
public class Hello {
    String value;
}
