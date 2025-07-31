package com.master.selenium.pages;

import org.openqa.selenium.WebDriver;

import com.master.selenium.core.BasePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;
import com.master.selenium.utils.ElementUtils;

@Component
public class MainPage extends BasePage{

    @FindBy(className = "inventory_list")
    private WebElement inventoryList;

    @FindBy(className = "inventory_item")
    private java.util.List<WebElement> inventoryItems;

    @FindBy(css = ".btn_inventory")
    private java.util.List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;

    @FindBy(className = "product_sort_container")
    private WebElement filterDropdown;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getInventoryList() {
        return inventoryList;
    }

    public java.util.List<WebElement> getInventoryItems() {
        return inventoryItems;
    }

    public java.util.List<WebElement> getAddToCartButtons() {
        return addToCartButtons;
    }

    public WebElement getCartIcon() {
        return cartIcon;
    }

    public WebElement getBurgerMenuButton() {
        return burgerMenuButton;
    }

    public WebElement getFilterDropdown() {
        return filterDropdown;
    }

    // ---- Common Actions ----

    /** Adds product to cart by its index in the inventory list */
    public void addProductToCartByIndex(int index) {
        if (addToCartButtons != null && index >= 0 && index < addToCartButtons.size()) {
            ElementUtils.clickElement(driver, addToCartButtons.get(index));
        } else {
            throw new IllegalArgumentException("Invalid product index for Add to Cart button: " + index);
        }
    }

    /** Adds product to cart by the product name (visible text) */
    public void addProductToCartByName(String name) {
        for (int i = 0; i < inventoryItems.size(); i++) {
            WebElement item = inventoryItems.get(i);
            WebElement titleElem = item.findElement(By.className("inventory_item_name"));
            if (titleElem.getText().trim().equalsIgnoreCase(name.trim())) {
                ElementUtils.clickElement(driver, addToCartButtons.get(i));
                return;
            }
        }
        throw new IllegalArgumentException("Product with name '" + name + "' not found.");
    }

    /** Returns the number of products listed on the page */
    public int getNumberOfProducts() {
        return inventoryItems != null ? inventoryItems.size() : 0;
    }

    /** Opens the cart page by clicking the cart icon */
    public void openCart() {
        ElementUtils.clickElement(driver, cartIcon);
    }

    /** Opens the menu (burger) */
    public void clickMenu() {
        ElementUtils.clickElement(driver, burgerMenuButton);
    }

    /** Selects a sort/filter option by visible text */
    public void selectSortOption(String optionText) {
        Select select = new Select(filterDropdown);
        select.selectByVisibleText(optionText);
    }
}
