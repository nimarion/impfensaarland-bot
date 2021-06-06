package de.nmarion.impfbot.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> findAll(String xPath) {
        return driver.findElements(By.xpath(xPath));
    }

    public WebElement getMainContent(){
        List<WebElement> list = findAll("//*[@id='logged-in-area']");
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }


    public boolean clickNext() {
        List<WebElement> list = findAll("//button[@type='submit' and contains(., 'Weiter')]");
        if (!list.isEmpty()) {
            list.get(0).click();
            return true;
        }
        return false;
    }

    public boolean clickBack() {
        List<WebElement> list = findAll("//button[@type='submit' and contains(., 'Zurück')]");
        if (!list.isEmpty()) {
            list.get(0).click();
            return true;
        }
        return false;
    }

    public boolean isLoading(){
        return !findAll("//div[contains(@class, 'LoadingSpinner__Wrapper')]").isEmpty();
    }

    public boolean isDisplayed(){
        return false;
    }


}