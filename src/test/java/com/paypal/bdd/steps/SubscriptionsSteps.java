package com.paypal.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import com.paypal.core.ApiClient;
import com.paypal.bdd.hooks.CucumberHooks;
import com.aventstack.extentreports.ExtentTest;

import static org.testng.Assert.*;

public class SubscriptionsSteps {
    private Response response;
    private String subscriptionPayload = "{ \"plan_id\": \"P-123\", \"subscriber\": { \"name\": { \"given_name\": \"John\", \"surname\": \"Doe\" }, \"email_address\": \"example@example.com\" } }";

    @Given("I have a valid subscription payload")
    public void i_have_a_valid_subscription_payload() {
        assertNotNull(subscriptionPayload);
    }

    @When("I create the subscription")
    public void i_create_the_subscription() {
        response = ApiClient.spec()
                .body(subscriptionPayload)
                .when().post("/v1/billing/subscriptions")
                .then().extract().response();
    }

    @Then("the API should return a 201 Created response")
    public void the_api_should_return_a_201_created_response() {
        ExtentTest t = CucumberHooks.getScenarioTest();
        try {
            int code = response.getStatusCode();
            assertEquals(code, 201, "Expected 201 Created");
            if (t != null) t.pass("Subscription created: status 201");
        } catch (AssertionError ae) {
            if (t != null) {
                try { t.fail("Assertion failed: " + ae.getMessage()); } catch (Exception ignored) {}
                try { t.info("Response body:\n" + response.asString()); } catch (Exception ignored) {}
            }
            throw ae;
        }
    }
}
