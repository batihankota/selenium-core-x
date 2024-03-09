package definitions;

import settings.DriverSettings;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowDefinitions {

    private static final Logger logger = Logger.getLogger(ShadowDefinitions.class);
    private static final WebDriver driver = DriverSettings.getWebDriver();
    private static final JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

    // Shadow DOM içindeki bir elemente tıklar
    public static void clickShadowElement(String shadowHostCssSelector, String shadowCssSelector) {
        try {
            WebElement shadowHost = driver.findElement(By.cssSelector(shadowHostCssSelector));
            WebElement shadowRoot = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
            WebElement shadowElement = findShadowElement(shadowRoot, shadowCssSelector);

            if (shadowElement != null) {
                jsExecutor.executeScript("arguments[0].click();", shadowElement);
                logger.info("Shadow elemente tıklandı: " + shadowCssSelector);
            } else {
                logger.error("Shadow element bulunamadı.");
            }
        } catch (Exception e) {
            logger.error("Shadow elemente tıklanırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    // Shadow DOM içindeki bir elementi bulur
    private static WebElement findShadowElement(WebElement shadowRoot, String shadowCssSelector) {
        try {
            return (WebElement) jsExecutor.executeScript(
                    "return arguments[0].querySelector(arguments[1]);", shadowRoot, shadowCssSelector);
        } catch (Exception e) {
            logger.error("Shadow element bulunurken bir hata oluştu: " + e.getMessage());
            return null;
        }
    }

    // Shadow DOM içindeki bir elementin metnini alır
    public static String getShadowElementText(String shadowHostCssSelector, String shadowCssSelector) {
        try {
            WebElement shadowHost = driver.findElement(By.cssSelector(shadowHostCssSelector));
            WebElement shadowRoot = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
            WebElement shadowElement = findShadowElement(shadowRoot, shadowCssSelector);

            if (shadowElement != null) {
                return shadowElement.getText();
            } else {
                logger.error("Shadow element bulunamadı.");
                return null;
            }
        } catch (Exception e) {
            logger.error("Shadow element metni alınırken bir hata oluştu: " + e.getMessage());
            return null;
        }
    }

    // Shadow DOM içindeki bir elementin değerini değiştirir
    public static void setShadowElementText(String shadowHostCssSelector, String shadowCssSelector, String text) {
        try {
            WebElement shadowHost = driver.findElement(By.cssSelector(shadowHostCssSelector));
            WebElement shadowRoot = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
            WebElement shadowElement = findShadowElement(shadowRoot, shadowCssSelector);

            if (shadowElement != null) {
                jsExecutor.executeScript("arguments[0].innerText = arguments[1];", shadowElement, text);
                logger.info("Shadow elementin metni güncellendi: " + shadowCssSelector);
            } else {
                logger.error("Shadow element bulunamadı.");
            }
        } catch (Exception e) {
            logger.error("Shadow element metni güncellenirken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }
}
