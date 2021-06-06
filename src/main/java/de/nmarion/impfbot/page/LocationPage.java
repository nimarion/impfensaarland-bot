package de.nmarion.impfbot.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationPage extends AbstractPage{

    public LocationPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getLocations(){
        return findAll("//button[@type='button']");
    }

    public WebElement getLocation(final String location){
        List<WebElement> list = findAll(String.format("//button[@type='button' and contains(., '%s')]", location));
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public boolean chooseLocation(final String location) {
        List<WebElement> list = findAll(String.format("//button[@type='button' and contains(., '%s')]", location));
        if (!list.isEmpty()) {
            list.get(0).click();
            return true;
        }
        return false;
    }

}
