package com.lopixlabs.app.fullstackdemo.hello;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class Hello {
    String value;
}
