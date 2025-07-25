package com.master.selenium.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility for capturing screenshots using WebDriver.
 */
public class ScreenshotUtils {

    /**
     * Captures a screenshot and saves it to the screenshots directory.
     * @param driver WebDriver instance
     * @param fileNameHint A hint string to include in the file name (test or context)
     */
    public static void captureScreenshot(WebDriver driver, String fileNameHint) {
        if (driver instanceof TakesScreenshot) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotDir = "screenshots";
                Files.createDirectories(Paths.get(screenshotDir));
                String fileName = String.format("%s/%s_%d.png", screenshotDir, fileNameHint, System.currentTimeMillis());
                Files.copy(screenshot.toPath(), Paths.get(fileName));
                System.out.println("Screenshot captured: " + fileName);
            } catch (IOException ex) {
                System.err.println("Failed to capture screenshot: " + ex.getMessage());
            }
        }
    }
}
