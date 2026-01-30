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
//public class OrdersSteps {
//    private Response response;
//    private String orderPayload = "{ \"intent\": \"CAPTURE\", \"purchase_units\": [ { \"amount\": { \"currency_code\": \"USD\", \"value\": \"1.00\" } } ] }";
//
//    @Given("I have a valid order payload")
//    public void i_have_a_valid_order_payload() {
//        assertNotNull(orderPayload);
//    }
//
//    @When("I create the order")
//    public void i_create_the_order() {
//        response = ApiClient.spec()
//                .body(orderPayload)
//                .when().post("/v2/checkout/orders")
//                .then().extract().response();
//    }
//
//    @Then("the API should return a 201 Created response")
//    public void the_api_should_return_a_201_created_response() {
//        ExtentTest t = CucumberHooks.getScenarioTest();
//        try {
//            int code = response.getStatusCode();
//            assertEquals(code, 201, "Expected 201 Created");
//            if (t != null) t.pass("Order created: status 201");
//        } catch (AssertionError ae) {
//            if (t != null) {
//                try { t.fail("Assertion failed: " + ae.getMessage()); } catch (Exception ignored) {}
//                try { t.info("Response body:\n" + response.asString()); } catch (Exception ignored) {}
//            }
//            throw ae;
//        }
//    }
//}
