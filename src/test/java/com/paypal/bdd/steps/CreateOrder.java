package com.paypal.bdd.steps;

import com.paypal.bdd.hooks.TestContext;
import com.paypal.core.ConfigReader;
import com.paypal.util.JsonGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class CreateOrder {
    Response response;

    @Given("I create the order with intent {string}")
    public void iCreateTheOrderWithIntent(String intent) {
        if (intent.isBlank())
            throw new IllegalArgumentException("Intent value is missing");

        var requestBody = JsonGenerator.generateJson("AUTHORIZE");

        response = given().baseUri(ConfigReader.get("base.uri"))
                .header("Authorization", "Bearer " + TestContext.get("access_token"))
                .header("Prefer","return=representation").header("Content-Type", "application/json")
                .header("PayPal-Request-Id","A v4 style guid")
                .body(requestBody)
                .when()
                .post("/v2/checkout/orders");

        // Print the response (for demonstration)
        System.out.println("Response Body: " + response.getBody().asString());

        System.out.println(response.asPrettyString());
    }

    @Then("I validate the order response")
    public void iValidateTheOrderResponse() {
        if (response == null) {
            throw new IllegalStateException("Response is null. Make sure to create the order first.");
        }

        int statusCode = response.getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            throw new AssertionError("Expected status code 201 but got " + statusCode);
        }

        String orderId = response.jsonPath().getString("id");
        if (orderId == null || orderId.isEmpty()) {
            throw new AssertionError("Order ID is missing in the response");
        }

        System.out.println("Order created successfully with ID: " + orderId);

        String intent = response.jsonPath().getString("intent");
        if (intent == null || intent.isEmpty()) {
            throw new AssertionError("intent is missing in the response");
        }

        System.out.println("intent created successfully with ID: " + intent);
    }
}

