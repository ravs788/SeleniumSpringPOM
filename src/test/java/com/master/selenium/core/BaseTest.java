package com.master.selenium.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.qameta.allure.Allure;

/**
 * BaseTest: Sets up and tears down WebDriver for UI tests.
 */
public abstract class BaseTest {

    @Autowired
    protected WebDriver driver;

    @Value("${app.baseUrl:https://www.saucedemo.com/v1/}")
    protected String baseUrl;

    @BeforeEach
    public void setUp() {
        driver.get(baseUrl);
    }

    /**
     * Utility to run test steps and automatically capture screenshot on failure,
     * and attach the screenshot to Allure report.
     * @param testLogic The test logic as a Runnable or lambda
     * @param testName  Name for screenshot file
     */
    protected void runWithScreenshotOnFailure(Runnable testLogic, String testName) {
        try {
            testLogic.run();
        } catch (AssertionError | Exception ex) {
            String screenshotPath = com.master.selenium.utils.ScreenshotUtils.captureScreenshot(this.driver, testName);
            if (screenshotPath != null) {
                try {
                    byte[] bytes = Files.readAllBytes(Paths.get(screenshotPath));
                    Allure.addAttachment("Failure Screenshot", "image/png", new java.io.ByteArrayInputStream(bytes), ".png");
                } catch (IOException e) {
                    System.err.println("Failed to attach screenshot to Allure: " + e.getMessage());
                }
            }
            throw ex;
        }
    }

    @AfterEach
    public void tearDown() {
        
            driver.quit();
        
    }
}
