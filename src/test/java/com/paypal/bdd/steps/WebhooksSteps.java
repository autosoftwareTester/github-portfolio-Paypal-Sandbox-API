package com.paypal.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import com.paypal.core.ApiClient;
import com.paypal.bdd.hooks.CucumberHooks;
import com.aventstack.extentreports.ExtentTest;

import static org.testng.Assert.*;

public class WebhooksSteps {
    private Response response;
    private String webhookPayload = "{ \"id\": \"WH-123\", \"event_type\": \"PAYMENT.SALE.COMPLETED\" }";

    @Given("I have a sample webhook event")
    public void i_have_a_sample_webhook_event() {
        assertNotNull(webhookPayload);
    }

    @When("I POST the webhook to the verification endpoint")
    public void i_post_the_webhook_to_the_verification_endpoint() {
        response = ApiClient.spec()
                .body(webhookPayload)
                .when().post("/v1/notifications/verify-webhook-signature")
                .then().extract().response();
    }

    @Then("the API should return 200 OK")
    public void the_api_should_return_200_ok() {
        ExtentTest t = CucumberHooks.getScenarioTest();
        try {
            int code = response.getStatusCode();
            assertEquals(code, 200, "Expected 200 OK");
            if (t != null) t.pass("Webhook verified: status 200");
        } catch (AssertionError ae) {
            if (t != null) {
                try { t.fail("Assertion failed: " + ae.getMessage()); } catch (Exception ignored) {}
                try { t.info("Response body:\n" + response.asString()); } catch (Exception ignored) {}
            }
            throw ae;
        }
    }
}
