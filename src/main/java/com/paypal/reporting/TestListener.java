package com.paypal.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;

import java.util.HashMap;
import java.util.Map;

public class TestListener implements ITestListener, ISuiteListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static final Map<String, ExtentTest> tests = new HashMap<>();

    @Override
    public void onStart(ISuite suite) {
        // suite start
    }

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest t = extent.createTest(testName);
        testThread.set(t);
        tests.put(getKey(result), t);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        tests.get(getKey(result)).pass("PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        tests.get(getKey(result)).fail(result.getThrowable());
    }

    @Override public void onTestSkipped(ITestResult result) { tests.get(getKey(result)).skip(result.getThrowable()); }
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}

    private String getKey(ITestResult r) {
        return r.getTestClass().getName() + "." + r.getMethod().getMethodName();
    }
}

