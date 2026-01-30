//package org.example.paypal.bdd.steps;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import io.cucumber.java.en.Then;
//import io.restassured.response.Response;
//import org.example.paypal.core.ApiClient;
//import org.example.paypal.bdd.hooks.CucumberHooks;
//import com.aventstack.extentreports.ExtentTest;
//
//import static org.testng.Assert.*;
//
//public class PayoutsSteps {
//    private Response response;
//    private String payoutPayload = "{ \"sender_batch_header\": { \"sender_batch_id\": \"batch_" + System.currentTimeMillis() + "\", \"email_subject\": \"You have a payout!\" }, \"items\": [ { \"recipient_type\": \"EMAIL\", \"amount\": { \"value\": \"1.00\", \"currency\": \"USD\" }, \"receiver\": \"example@example.com\" } ] }";
//
//    @Given("I have a valid payout payload")
//    public void i_have_a_valid_payout_payload() {
//        assertNotNull(payoutPayload);
//    }
//
//    @When("I create the payout")
//    public void i_create_the_payout() {
//        response = ApiClient.spec()
//                .body(payoutPayload)
//                .when().post("/v1/payments/payouts")
//                .then().extract().response();
//    }
//
//    @Then("the API should return a 201 Created response")
//    public void the_api_should_return_a_201_created_response() {
//        ExtentTest t = CucumberHooks.getScenarioTest();
//        try {
//            int code = response.getStatusCode();
//            assertEquals(code, 201, "Expected 201 Created");
//            if (t != null) t.pass("Payout created: status 201");
//        } catch (AssertionError ae) {
//            if (t != null) {
//                try { t.fail("Assertion failed: " + ae.getMessage()); } catch (Exception ignored) {}
//                try { t.info("Response body:\n" + response.asString()); } catch (Exception ignored) {}
//            }
//            throw ae;
//        }
//    }
//}
