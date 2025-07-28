package com.master.selenium.tests;

import com.master.selenium.core.BaseTest;
import com.master.selenium.pages.LoginPage;
import com.master.selenium.pages.MainPage;
import com.master.selenium.pages.CartPage;
import com.master.selenium.pages.CheckoutPage;
import com.master.selenium.pages.CheckoutOverviewPage;
import com.master.selenium.pages.CheckoutCompletePage;
import com.master.selenium.utils.AssertUtils;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = com.master.selenium.DemoApplication.class)
@Epic("SauceDemo UI Tests")
@Feature("Checkout Flow")
public class CheckoutFlowTest extends BaseTest {

    @Value("${app.login.username}")
    private String username;

    @Value("${app.login.password}")
    private String password;

    @Autowired
    private LoginPage loginPage;
    @Autowired
    private MainPage mainPage;
    @Autowired
    private CartPage cartPage;
    @Autowired
    private CheckoutPage checkoutPage;
    @Autowired
    private CheckoutOverviewPage checkoutOverviewPage;
    @Autowired
    private CheckoutCompletePage checkoutCompletePage;

    @Test
    @Story("Complete Checkout Flow")
    @Severity(SeverityLevel.BLOCKER)
    @Description("End-to-end: login, add items to cart, checkout, and verify order completion")
    public void testEndToEndCheckoutFlow() {
        runWithScreenshotOnFailure(() -> {
            // Login
            loginPage.login(username, password);

            // Add two products to cart (note: use correct product names from the UI)
            mainPage.addProductToCartByName("Sauce Labs Backpack");
            mainPage.addProductToCartByName("Sauce Labs Bike Light");

            // Open cart and proceed to checkout
            mainPage.openCart();
            cartPage.clickCheckout();

            // Fill checkout form (example data)
            checkoutPage.fillCheckoutForm("John", "Doe", "12345");

            // Finish order
            checkoutOverviewPage.clickFinish();

            // Assert confirmation
            String confirmationText = checkoutCompletePage.getCompleteHeaderText().toLowerCase();
            AssertUtils.assertTrue(
                driver,
                confirmationText.contains("thank you for your order"),
                "Order completion message not displayed"
            );
        }, "testEndToEndCheckoutFlow");
    }
}
