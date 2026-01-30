package com.paypal.core;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient {
    private static RequestSpecification defaultSpec;

    public static RequestSpecification spec() {
        if (defaultSpec == null) {
            String base = ConfigReader.get("base.uri");
            if (base != null && !base.isEmpty()) RestAssured.baseURI = base;
            defaultSpec = given()
                    .relaxedHTTPSValidation()
                    .contentType("application/json")
                    .accept("application/json")
                    .header("Accept-Language", "en_US")
                    .log().ifValidationFails();
        }
        return defaultSpec;
    }
}

