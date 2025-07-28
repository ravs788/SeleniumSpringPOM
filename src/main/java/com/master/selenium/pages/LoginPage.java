package com.master.selenium.pages;

import com.master.selenium.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * Page Object for SauceDemo Login Page.
 */
@Component
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    // Error message, typical for SauceDemo (adjust selector as needed)
    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        SetTextBox(usernameInput, username);
        SetTextBox(passwordInput, password);
        ClickElement(loginButton);
    }

    /** Returns the login error message text, or an empty string if not present. */
    public String getLoginErrorMessage() {
        try {
            return errorMessage.isDisplayed() ? errorMessage.getText() : "";
        } catch (Exception e) {
            return "";
        }
    }
}
