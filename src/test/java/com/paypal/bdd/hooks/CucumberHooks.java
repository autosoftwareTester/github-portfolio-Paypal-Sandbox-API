package com.paypal.bdd.hooks;

import com.paypal.core.ApiClient;
import com.paypal.core.ConfigReader;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import com.paypal.reporting.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;

import static com.paypal.bdd.hooks.TestContext.get;
import static com.paypal.bdd.hooks.TestContext.set;
import static org.testng.Assert.assertNotNull;

public class CucumberHooks {
    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();
    private Response response;

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest t = extent.createTest(scenario.getName());
        scenarioTest.set(t);

        generate_access_token();
        store_access_token();
    }

    private void generate_access_token() {
        String clientId = ConfigReader.get("client.id");
        String clientSecret = ConfigReader.get("client.secret");
        response = ApiClient.spec()
                .auth().preemptive().basic(clientId, clientSecret)
                .formParam("grant_type", "client_credentials")
                .when().post("/v1/oauth2/token")
                .then().log().ifValidationFails()
                .extract().response();
    }

    private void store_access_token() {
        ExtentTest t = CucumberHooks.getScenarioTest();
        try {
            String token = response.jsonPath().getString("access_token");
            assertNotNull(token, "access_token should be present");

            // Store token in test context for later use
            set("access_token", token);
            if (t != null) t.pass("Access token received");
        } catch (AssertionError ae) {
            if (t != null) {
                try {
                    t.fail("Assertion failed: " + ae.getMessage());
                } catch (Exception ignored) {
                }
                try {
                    t.info("Response body:\n" + response.asString());
                } catch (Exception ignored) {
                }
            }
            throw ae;
        }
    }

    public static Object get_access_token(String key) {
        return get(key);
    }

    @After
    public void afterScenario(Scenario scenario) {
        ExtentTest t = scenarioTest.get();
        if (t != null) {
            if (scenario.isFailed()) {
                t.fail("Scenario failed");
            } else {
                t.pass("PASSED");
            }
        }
        // Do not flush here; TestListener will flush at suite end
    }

    public static ExtentTest getScenarioTest() {
        return scenarioTest.get();
    }
}
