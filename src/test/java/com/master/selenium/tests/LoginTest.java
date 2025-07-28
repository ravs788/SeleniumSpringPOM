package com.master.selenium.tests;

import com.master.selenium.core.BaseTest;
import com.master.selenium.pages.LoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import com.master.selenium.utils.AssertUtils;

/**
 * Sample login test for saucedemo using POM and Spring Boot.
 */
@SpringBootTest(classes = com.master.selenium.DemoApplication.class)
@Epic("SauceDemo UI Tests")
@Feature("Login Feature")
public class LoginTest extends BaseTest {

    @Value("${app.login.username}")
    private String username;

    @Value("${app.login.password}")
    private String password;

    @Autowired
    private LoginPage loginPage;

    @Value("${app.login.username.incorrect}")
    private String invalidUsername;

    @Test
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test successful login with valid credentials using the page object model")
    public void testSuccessfulLogin() {
        runWithScreenshotOnFailure(() -> {
            loginPage.login(username, password);

            // Assert that we are redirected to the inventory page after successful login
            AssertUtils.assertTrue(
                driver,
                driver.getCurrentUrl().contains("inventory.html"),
                "Login did not redirect to the inventory page"
            );
        }, "testSuccessfulLogin");
    }

    @Test
    @Story("Invalid Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test unsuccessful login with an incorrect username, expecting login error message")
    public void testInvalidLogin() {
        runWithScreenshotOnFailure(() -> {
            loginPage.login(invalidUsername, password);
            // Example: Check for error message or failed login indicator.
            // Adjust according to your LoginPage implementation.
            boolean isErrorShown = false;
            try {
                isErrorShown = loginPage.getLoginErrorMessage() != null
                    && !loginPage.getLoginErrorMessage().isEmpty();
            } catch (Exception ignored) {}

            AssertUtils.assertTrue(
                driver,
                isErrorShown,
                "Invalid login did not show error message as expected"
            );
        }, "testInvalidLogin");
    }
}
