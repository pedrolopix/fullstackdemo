package com.lopixlabs.app.fullstackdemo.auth;

import static io.restassured.RestAssured.given;

import com.lopixlabs.app.fullstackdemo.auth.model.AuthResult;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import java.util.Base64;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AuthResourceTest {

    @Test
    void shouldLogin() {
        final AuthResult loginResult = given()
            .when()
            .body("""
                {
                "username": "user",
                "password": "password"
                }
                """)
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .post("/api/auth/signin")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().body().as(AuthResult.class);

    }

}
