package com.lopixlabs.app.fullstackdemo.login;

import static io.restassured.RestAssured.given;

import com.lopixlabs.app.fullstackdemo.login.model.LoginResult;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import java.util.Base64;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(LoginResource.class)
class LoginResourceTest {

    @Test
    void shouldNotAccessAdminWhenAnonymous() {
        given().
            get("/api/me")
            .then()
            .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldLogin() {
        final LoginResult loginResult = given()
            .when()
            .auth().basic("user", "pass")
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .get("/api/login")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().body().as(LoginResult.class);

        given()
            .when()
            .auth().oauth2(loginResult.getToken())
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .post("/me")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract().body().as(LoginResult.class);
    }

    private String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
