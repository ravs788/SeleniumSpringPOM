package com.master.selenium.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BasePage: Parent class for all Page Objects. Wraps WebDriver and common page methods.
 */
public abstract class BasePage {

    @Autowired
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Add more base WebDriver methods if needed.

    /**
     * Scrolls the given element into view.
     */
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    /**
     * Waits for the given element to be visible using FluentWait.
     */
    private void waitForVisibility(WebElement element) {
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
    private void waitForEnabled(WebElement element) {
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
    protected void SetTextBox(WebElement element, String text) {
        scrollToElement(element);
        waitForVisibility(element);
        waitForEnabled(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Scrolls to the element, waits for visibility and enabled, then clicks.
     */
    protected void ClickElement(WebElement element) {
        scrollToElement(element);
        waitForVisibility(element);
        waitForEnabled(element);
        element.click();
    }

    /**
     * Scrolls to the element, waits for visibility and enabled, then clears the element.
     */
    protected void ClearElement(WebElement element) {
        scrollToElement(element);
        waitForVisibility(element);
        waitForEnabled(element);
        element.clear();
    }
}
