package com.master.selenium.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ElementUtils {

    /**
     * Scrolls the given element into view.
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    /**
     * Waits for the given element to be visible using FluentWait.
     */
    public static void waitForVisibility(WebElement element) {
        new FluentWait<>(element)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class)
            .until(el -> el.isDisplayed());
    }

    /**
     * Waits for the given element to be enabled using FluentWait.
     */
    public static void waitForEnabled(WebElement element) {
        new FluentWait<>(element)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class)
            .until(el -> el.isEnabled());
    }

    /**
     * Scrolls to the element, waits for visibility and enabled, then sends keys.
     */
    public static void setTextBox(WebDriver driver, WebElement element, String text) {
        scrollToElement(driver, element);
        waitForVisibility(element);
        waitForEnabled(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Scrolls to the element, waits for visibility and enabled, then clicks.
     */
    public static void clickElement(WebDriver driver, WebElement element) {
        scrollToElement(driver, element);
        waitForVisibility(element);
        waitForEnabled(element);
        element.click();
    }

    /**
     * Scrolls to the element, waits for visibility and enabled, then clears the element.
     */
    public static void clearElement(WebDriver driver, WebElement element) {
        scrollToElement(driver, element);
        waitForVisibility(element);
        waitForEnabled(element);
        element.clear();
    }
}