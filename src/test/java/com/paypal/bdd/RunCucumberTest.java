package com.paypal.bdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/orders",
        glue = {"com.paypal.bdd"},
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
