package de.nmarion.impfbot;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.nmarion.impfbot.page.AbstractPage;
import de.nmarion.impfbot.page.CheckoutPage;
import de.nmarion.impfbot.page.LocationPage;
import de.nmarion.impfbot.page.SlotPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private final WebDriver webDriver;
    private Telegram telegram;

    public Main(String... args) {
        if(Configuration.TELEGRAM_CHATID == null || Configuration.TELEGRAM_TOKEN == null){
            throw new IllegalStateException("Missing telegram configuration");
        }
        telegram = new Telegram(Configuration.TELEGRAM_TOKEN, Configuration.TELEGRAM_CHATID);
        webDriver = createWebDriver();
        webDriver.get("https://www.impfen-saarland.de/service/waitlist_entries");
        while (!checkFreeSlots(Configuration.LOCATION));
    }

    private boolean checkFreeSlots(final String location) {
        final LocationPage locationPage = new LocationPage(webDriver);
        locationPage.setLanguage();
        waitForLoading(locationPage);
        final WebElement locationButton = locationPage.getLocation(location);
        if (locationButton == null) {
            throw new IllegalArgumentException(location + " existiert nicht");
        }
        locationButton.click();
        locationPage.clickNext();
        final SlotPage slotPage = new SlotPage(webDriver);
        final CheckoutPage checkoutPage = new CheckoutPage(webDriver);
        waitForLoading(slotPage);
        final List<WebElement> webElements = slotPage.getFreeSlots();
        if (!webElements.isEmpty()) {
            LOGGER.info(webElements.size() + " Termine gefunden...");
            final String name = webElements.get(0).getText();
            LOGGER.info("Versuche " + name + " zu wählen");
            webElements.get(0).click();
            slotPage.clickNext();
            waitForLoading(slotPage);
            // TODO: Strange page behaviour
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (checkoutPage.isDisplayed()) {
                LOGGER.info("Fester Impftermin gefunden und ausgewählt");
                WebElement mainContent = checkoutPage.getMainContent();
                try {
                    telegram.sendImage(takeScreenshot(mainContent), "Impftermin gefunden: \n" + name);
                } catch (IOException e) {
                    telegram.sendMessage("Impftermin gefunden: \n" + name);
                    e.printStackTrace();
                }
                return true;
            }
        }
        slotPage.clickBack();
        return false;
    }

    private void waitForLoading(AbstractPage page) {
        while (page.isLoading()) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //https://stackoverflow.com/a/13834607
    private File takeScreenshot(final WebElement webElement) throws IOException {
        final File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        final BufferedImage fullImg;
        fullImg = ImageIO.read(screenshot);
        // Get the location of element on the page
        final Point point = webElement.getLocation();
        // Get width and height of the element
        final int eleWidth = webElement.getSize().getWidth();
        final int eleHeight = webElement.getSize().getHeight();
        // Crop the entire page screenshot to get only element screenshot
        final BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", screenshot);
        return screenshot;
    }

    private WebDriver createWebDriver() {
        if(Configuration.REMOTE_GRID != null){
            try {
                return new RemoteWebDriver(new URL(Configuration.REMOTE_GRID), new ChromeOptions());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        WebDriverManager.chromedriver().setup();
        var chromeOptions = new ChromeOptions();
        var chromeDriver = new ChromeDriver(chromeOptions);
        return chromeDriver;
    }
    

    public static void main(String... args) {
        new Main(args);
    }

}
