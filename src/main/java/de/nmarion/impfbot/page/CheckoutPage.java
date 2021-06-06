package de.nmarion.impfbot.page;

import org.openqa.selenium.WebDriver;

public class CheckoutPage extends AbstractPage{

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return !findAll("//*[text()[contains(.,'Impftermine bestätigen')]]").isEmpty();
    }
    
}
