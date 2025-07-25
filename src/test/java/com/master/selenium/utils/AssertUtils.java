package com.master.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Assertions;

/**
 * Utility wrappers for assertions that capture screenshots on failure.
 * Placed in test code to depend on JUnit.
 */
public class AssertUtils {

    public static void assertTrue(WebDriver driver, boolean condition, String message) {
        try {
            Assertions.assertTrue(condition, message);
        } catch (AssertionError e) {
            ScreenshotUtils.captureScreenshot(driver, "assertTrueFailure");
            throw e;
        }
    }

    public static void assertEquals(WebDriver driver, Object expected, Object actual, String message) {
        try {
            Assertions.assertEquals(expected, actual, message);
        } catch (AssertionError e) {
            ScreenshotUtils.captureScreenshot(driver, "assertEqualsFailure");
            throw e;
        }
    }

    public static void assertFalse(WebDriver driver, boolean condition, String message) {
        try {
            Assertions.assertFalse(condition, message);
        } catch (AssertionError e) {
            ScreenshotUtils.captureScreenshot(driver, "assertFalseFailure");
            throw e;
        }
    }

    public static void fail(WebDriver driver, String message) {
        try {
            Assertions.fail(message);
        } catch (AssertionError e) {
            ScreenshotUtils.captureScreenshot(driver, "assertFail");
            throw e;
        }
    }
}
