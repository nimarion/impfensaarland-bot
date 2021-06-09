package de.nmarion.impfbot.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import de.nmarion.impfbot.Configuration;

public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> findAll(String xPath) {
        return driver.findElements(By.xpath(xPath));
    }

    public WebElement getMainContent() {
        List<WebElement> list = findAll("//*[@id='logged-in-area']");
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public boolean clickNext() {
        List<WebElement> list = findAll("//button[@type='submit' and contains(., 'Weiter')]");
        if (!list.isEmpty()) {
            clickElement(list.get(0));
            return true;
        }
        return false;
    }

    public boolean setLanguage() {
        List<WebElement> list = findAll("//button[@type='button' and contains(., 'DE')]");
        if (!list.isEmpty()) {
            list.get(0).click();
            return true;
        }
        return false;
    }

    public boolean clickBack() {
        List<WebElement> list = findAll("//button[@type='submit' and contains(., 'Zurück')]");
        if (!list.isEmpty()) {
            clickElement(list.get(0));
            return true;
        }
        return false;
    }

    public boolean isLoading() {
        return !findAll("//div[contains(@class, 'LoadingSpinner__Wrapper')]").isEmpty();
    }

    public void clickElement(final WebElement webElement) {
        if (Configuration.DELAY != null && Configuration.DELAY.equalsIgnoreCase("true")) {
            int minDelay = 350;
            int minRange = 200;
            int maxRange = 400;
            try {
                TimeUnit.MILLISECONDS
                        .sleep(minDelay + (minRange + (int) (Math.random() * ((maxRange - minRange) + 1))));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        webElement.click();
    }

    public boolean isDisplayed() {
        return false;
    }

}