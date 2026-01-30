package com.paypal.bdd.steps.authorization;

import com.paypal.bdd.hooks.CucumberHooks;
import com.paypal.core.ApiClient;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserInformationStep {
    Response response;
    String token = "";

    @Before
    public void setup() {
        token = (String) CucumberHooks.get_access_token("access_token");
        assertNotNull(token, "access_token should be present");
    }

    @Given("I have a valid access token")
    public void iHaveAValidAccessToken() {
        assertNotNull(token, "access_token should be present");
        System.out.println(token);
    }

    @When("I request the user profile")
    public void iRequestTheUserProfile() {
        var queryParams = new HashMap<String, String>();
        queryParams.put("schema", "paypalv1.1");

        response = ApiClient.spec().queryParams(queryParams).get("/v1/identity/oauth2/userinfo")
                .then().log().ifValidationFails().extract().response();
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        assertEquals(response.statusCode(), statusCode);
    }

    @And("the response should contain {string}")
    public void theResponseShouldContain(String user_id) {
        assertNotNull(response.jsonPath().getString(user_id), user_id);
        System.out.println(response.asPrettyString());
    }
}
