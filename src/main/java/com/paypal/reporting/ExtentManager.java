package com.paypal.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.paypal.core.ConfigReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            String baseDir = ConfigReader.get("report.dir");
            if (baseDir == null) baseDir = "target/extent-reports";
            try {
                Path p = Paths.get(baseDir);
                Files.createDirectories(p);
            } catch (Exception ignored) {}
            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String reportPath = baseDir + "/extent_" + ts + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("PayPal API Test Report");
            spark.config().setReportName("PayPal API Tests");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}

