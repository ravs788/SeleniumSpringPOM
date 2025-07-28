package com.master.selenium.pages;

import com.master.selenium.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    // Robust selector: try button with text and known checkout classes
    @FindBy(css = ".btn_action.checkout_button")
    private WebElement checkoutButton;

    @FindBy(className = "cart_quantity")
    private List<WebElement> cartQuantities;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> cartItemNames;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public void clickCheckout() {
        ClickElement(checkoutButton);
    }

    public int getCartSize() {
        return cartItems != null ? cartItems.size() : 0;
    }

    public String getCartItemName(int index) {
        return (cartItemNames != null && index >= 0 && index < cartItemNames.size())
                ? cartItemNames.get(index).getText() : null;
    }

    public String getCartItemQuantity(int index) {
        return (cartQuantities != null && index >= 0 && index < cartQuantities.size())
                ? cartQuantities.get(index).getText() : null;
    }
}
