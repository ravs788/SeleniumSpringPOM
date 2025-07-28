package com.master.selenium.pages;

import com.master.selenium.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(css = "input[value='CONTINUE']")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        SetTextBox(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        SetTextBox(lastNameInput, lastName);
    }

    public void enterPostalCode(String postalCode) {
        SetTextBox(postalCodeInput, postalCode);
    }

    public void clickContinue() {
        ClickElement(continueButton);
    }

    public void clickCancel() {
        ClickElement(cancelButton);
    }

    public void fillCheckoutForm(String first, String last, String postal) {
        enterFirstName(first);
        enterLastName(last);
        enterPostalCode(postal);
        clickContinue();
    }
}
