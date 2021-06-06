package de.nmarion.impfbot.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SlotPage extends AbstractPage {

    public SlotPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasFreeSlots() {
        return !findAll("//*[text()[contains(.,'Impftermine auswählen')]]").isEmpty();
    }

    public boolean hasAlternativeSlots() {
        return !findAll("//*[text()[contains(.,'Bitte wählen Sie einen anderen Termin')]]").isEmpty();
    }

    public boolean noSlotsAvailable() {
        return !findAll("//*[text()[contains(.,'Keine Termine verfügbar')]]").isEmpty();
    }

    public List<WebElement> getFreeSlots() {
        return findAll("//div[contains(@class, 'TimeSelectorButton')]");
    }

    @Override
    public boolean isDisplayed() {
        return hasFreeSlots() || hasAlternativeSlots() || noSlotsAvailable();
    }

}
