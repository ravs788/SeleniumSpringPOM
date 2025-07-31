package com.master.selenium.pages;

import com.master.selenium.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import com.master.selenium.utils.ElementUtils;

@Component
public class CheckoutCompletePage extends BasePage {

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteHeaderText() {
        return completeHeader.getText();
    }

    public void clickBackHome() {
        ElementUtils.clickElement(driver, backHomeButton);
    }
}
