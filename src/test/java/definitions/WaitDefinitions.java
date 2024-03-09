package definitions;

import settings.DriverSettings;
import settings.SelectorType;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helpers.SelectorHelper.getSelector;


public class WaitDefinitions {

    private static final Logger logger = Logger.getLogger(ElementDefinitions.class);

    private static WebDriver getDriver() {
        return DriverSettings.getWebDriver();
    }


    // Belirtilen elementin görünür olmasını beklemek
    public static void waitUntilElementIsDisplayed(String locator, int timeoutInSeconds, SelectorType selectorType) {
        try {
            By byLocator = getSelector(locator, selectorType);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
            logger.info("Element görünür olana kadar beklenildi: " + locator + " (" + selectorType + ")");
        } catch (Exception e) {
            logger.error("Element görünür olana kadar bekleme sırasında bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    // Sayfanın yüklenmesini bekler
    public static void waitForPageLoad(int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            logger.info("Sayfa yükleme tamamlandı");
        } catch (Exception e) {
            logger.error("Sayfa yüklenirken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }
}
