package com.lopixlabs.app.fullstackdemo;

import lombok.Data;

@Data(staticConstructor = "of")
public class Hello {
    String value;
}
