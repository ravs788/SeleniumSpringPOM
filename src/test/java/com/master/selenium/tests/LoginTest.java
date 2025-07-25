package com.master.selenium.tests;

import com.master.selenium.core.BaseTest;
import com.master.selenium.pages.LoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Sample login test for saucedemo using POM and Spring Boot.
 */
@SpringBootTest(classes = com.master.selenium.DemoApplication.class)
@Epic("SauceDemo UI Tests")
@Feature("Login Feature")
public class LoginTest extends BaseTest {

    @org.springframework.beans.factory.annotation.Value("${app.login.username}")
    private String username;

    @org.springframework.beans.factory.annotation.Value("${app.login.password}")
    private String password;

    @Autowired
    private LoginPage loginPage;

    @Test
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test successful login with valid credentials using the page object model")
    public void testSuccessfulLogin() {
        runWithScreenshotOnFailure(() -> {
            loginPage.login(username, password);

            // Assert that we are redirected to the inventory page after successful login
            assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Login did not redirect to the inventory page"
            );
        }, "testSuccessfulLogin");
    }
}
