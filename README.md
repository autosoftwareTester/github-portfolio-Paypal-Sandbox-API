# PayPal API Test Automation

Maven + TestNG + Rest-Assured + ExtentReports scaffold for testing PayPal APIs.

Quick start:

1. Open `src/test/resources/config.properties` and set `client.id` and `client.secret`.
2. Run tests:
   - Windows PowerShell: `./run-tests.ps1`
   - Unix: `./run-tests.sh`
   - Or: `mvn clean test`

Reports:
- Extent report HTML files will be generated under `target/extent-reports` by default.
- Cucumber report: `target/cucumber-report.html`

BDD Hybrid
- This project supports BDD via Cucumber + TestNG. Feature files go under `src/test/resources/features` and step definitions under `src/test/java/org/example/paypal/bdd`.
- The TestNG suite (`testng.xml`) includes the Cucumber TestNG runner `bdd.com.paypal.RunCucumberTest` so `mvn test` will execute feature scenarios.

Project layout (key paths):
- `src/main/java/org/example/paypal/core` - shared helpers (ConfigReader, ApiClient)
- `src/main/java/org/example/paypal/reporting` - ExtentReports manager and TestNG listener
- `src/test/java/org/example/paypal/*` - tests per API area (authorization, orders, payments...)
- `src/test/java/org/example/paypal/bdd` - Cucumber runner, hooks, and step definitions
- `src/test/resources/features` - feature files
- `src/test/resources/config.properties` - test configuration
- `testng.xml` - TestNG suite

Add tests under the corresponding package for each API area (see screenshot categories).
