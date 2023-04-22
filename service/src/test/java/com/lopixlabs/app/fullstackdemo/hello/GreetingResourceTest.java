package com.lopixlabs.app.fullstackdemo.hello;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello/permit-all")
          .then()
             .statusCode(200)
             .body("value",is("hello + anonymous, isHttps: false, authScheme: null, hasJWT: false"));
    }

}
