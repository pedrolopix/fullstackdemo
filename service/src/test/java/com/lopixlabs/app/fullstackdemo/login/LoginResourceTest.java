package com.lopixlabs.app.fullstackdemo.login;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import com.lopixlabs.app.fullstackdemo.login.model.Login;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(LoginResource.class)
class LoginResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
            .when()
            .body(Login.builder().authentication("user").build())
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .post("/login")
            .then()
            .statusCode(200);
    }
}
