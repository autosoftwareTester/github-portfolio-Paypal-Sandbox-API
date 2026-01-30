package com.paypal.core;

import org.testng.annotations.BeforeClass;

public class BaseTest {


    @BeforeClass(alwaysRun = true)
    public void setup() {
        // initialize any global setup here (already handled by ApiClient/ConfigReader)
        int timeout = ConfigReader.getInt("timeout.seconds", 30);
        // can set RestAssured timeouts here if desired

    }
}

