package com.master.selenium.pages;

import com.master.selenium.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CheckoutOverviewPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> overviewItems;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(css = ".btn_action.cart_button")
    private WebElement finishButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getOverviewItems() {
        return overviewItems;
    }

    public String getSubtotal() {
        return subtotalLabel.getText();
    }

    public String getTax() {
        return taxLabel.getText();
    }

    public String getTotal() {
        return totalLabel.getText();
    }

    public void clickFinish() {
        ClickElement(finishButton);
    }

    public void clickCancel() {
        ClickElement(cancelButton);
    }
}
