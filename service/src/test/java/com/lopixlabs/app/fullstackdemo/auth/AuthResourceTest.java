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
@TestHTTPEndpoint(AuthResource.class)
class AuthResourceTest {

    @Test
    void shouldNotAccessAdminWhenAnonymous() {
        given().
            get("/api/me")
            .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldLogin() {
        final AuthResult loginResult = given()
            .when()
            .auth().basic("user", "pass")
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .get("/api/login")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().body().as(AuthResult.class);

        given()
            .when()
            .auth().oauth2(loginResult.getToken())
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .post("/me")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().body().as(AuthResult.class);
    }

    private String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
