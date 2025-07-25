package com.master.selenium.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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
     * Utility to run test steps and automatically capture screenshot on failure.
     * @param testLogic The test logic as a Runnable or lambda
     * @param testName  Name for screenshot file
     */
    protected void runWithScreenshotOnFailure(Runnable testLogic, String testName) {
        try {
            testLogic.run();
        } catch (AssertionError | Exception ex) {
            com.master.selenium.utils.ScreenshotUtils.captureScreenshot(driver, testName);
            throw ex;
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
