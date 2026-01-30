package com.paypal.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.paypal.core.ApiClient;

import static org.testng.Assert.*;

public class PaymentsSteps {
    private Response response;
    private String paymentPayload = "{ \"amount\": { \"currency\": \"USD\", \"total\": \"1.00\" }, \"intent\": \"sale\" }";

    @Given("I have a valid payment payload")
    public void i_have_a_valid_payment_payload() {
        assertNotNull(paymentPayload);
    }

    @When("I submit the payment")
    public void i_submit_the_payment() {
        response = ApiClient.spec()
                .body(paymentPayload)
                .when().post("/v1/payments/payment")
                .then().extract().response();
    }

//    @Then("the API should return a 201 Created response")
//    public void the_api_should_return_a_201_created_response() {
//        ExtentTest t = CucumberHooks.getScenarioTest();
//        try {
//            int code = response.getStatusCode();
//            assertEquals(code, 201, "Expected 201 Created");
//            if (t != null) t.pass("Payment created: status 201");
//        } catch (AssertionError ae) {
//            if (t != null) {
//                try { t.fail("Assertion failed: " + ae.getMessage()); } catch (Exception ignored) {}
//                try { t.info("Response body:\n" + response.asString()); } catch (Exception ignored) {}
//            }
//            throw ae;
//        }
//    }
}
