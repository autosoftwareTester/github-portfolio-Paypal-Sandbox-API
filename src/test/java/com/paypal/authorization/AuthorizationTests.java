package com.paypal.authorization;

import com.paypal.core.ApiClient;
import com.paypal.core.BaseTest;
import com.paypal.core.ConfigReader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.given;

public class AuthorizationTests extends BaseTest {

    @Test(description = "Get OAuth token - client credentials")
    public void getOAuthToken() {
        String clientId = ConfigReader.get("client.id");
        String clientSecret = ConfigReader.get("client.secret");

        // Assert that clientId and clientSecret are not null
        assertNotNull(clientId, "client.id must be set in config.properties");
        assertNotNull(clientSecret, "client.secret must be set in config.properties");

        // Make OAuth token request using client credentials and extract access token
        Response r = ApiClient.spec()
                .auth().preemptive().basic(clientId, clientSecret)
                .formParam("grant_type", "client_credentials")
                .when().post("/v1/oauth2/token")
                .then().log().ifValidationFails()
                .statusCode(200)
                .extract().response();

        String token = r.jsonPath().getString("access_token");
        assertNotNull(token, "access_token should be present");
    }
}

