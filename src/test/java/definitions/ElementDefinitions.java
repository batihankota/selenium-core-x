package definitions;

import settings.DriverSettings;
import settings.SelectorType;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

import static helpers.SelectorHelper.getSelector;

public class ElementDefinitions {

    private static final Logger logger = Logger.getLogger(ElementDefinitions.class);

    private static WebDriver getDriver() {
        return DriverSettings.getWebDriver();
    }

    // Belirtilen elemente tıklama
    public static void clickToElement(String locator, SelectorType selectorType) {
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            element.click();
            logger.info("Elemente tıklandı: " + locator + " (" + selectorType + ")");
        } catch (Exception e) {
            logger.error("Elemente tıklanırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    // Belirtilen elemente metin yazma
    public static void writeTextToElement(String locator, String text, SelectorType selectorType) {
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            element.sendKeys(text);
            logger.info("Elemente metin yazıldı: " + text + " (" + selectorType + ")");
        } catch (Exception e) {
            logger.error("Elemente metin yazılırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }


    // Belirtilen elementi görünür olana kadar kaydırma
    public static void scrollElementIntoView(By locator) {
        try {
            WebElement element = getDriver().findElement(locator);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Element sayfaya kaydırıldı: " + locator.toString());
        } catch (Exception e) {
            logger.error("Element sayfaya kaydırılırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    // Belirtilen elementin metin değerini alır
    public static String getElementText(String locator, SelectorType selectorType) {
        String elementText = null;
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            elementText = element.getText();
            logger.info("Elementin metin değeri alındı: " + elementText + " (" + selectorType + ")");
        } catch (Exception e) {
            logger.error("Elementin metin değeri alınırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
        return elementText;
    }

    // Belirtilen elementin görünürlüğünü kontrol eder
    public static boolean isElementVisible(String locator, SelectorType selectorType) {
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            return element.isDisplayed();
        } catch (Exception e) {
            logger.error("Elementin görünürlüğü kontrol edilirken bir hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // Belirtilen elementin metin değeri belirli bir değere eşit mi kontrol eder
    public static boolean isElementTextEqualTo(String locator, SelectorType selectorType, String expectedText) {
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            String actualText = element.getText();
            return actualText.equals(expectedText);
        } catch (Exception e) {
            logger.error("Elementin metin değeri kontrol edilirken bir hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // Belirtilen elementin metin değeri belirli bir değeri içeriyor mu kontrol eder
    public static boolean doesElementTextContain(String locator, String expectedText, SelectorType selectorType) {
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            String actualText = element.getText();
            boolean result = actualText.contains(expectedText);
            if (result) {
                logger.info("Elementin metin değeri '" + actualText + "', beklenen metni içeriyor: '" + expectedText + "'");
            } else {
                logger.info("Elementin metin değeri '" + actualText + "', beklenen metni içermiyor: '" + expectedText + "'");
            }
            return result;
        } catch (Exception e) {
            logger.error("Elementin metin değeri kontrol edilirken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    // Belirtilen elementin belirli bir değerini alır
    public static String getElementAttribute(String locator, SelectorType selectorType, String attributeName) {
        String attributeValue = null;
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            attributeValue = element.getAttribute(attributeName);
            logger.info("Elementin " + attributeName + " değeri alındı: " + locator + " (" + selectorType + ")");
        } catch (Exception e) {
            logger.error("Elementin " + attributeName + " değeri alınırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
        return attributeValue;
    }

    // Belirtilen elementin içeriğini temizler ve belirli bir metni gönderir
    public static void clearAndSendKeys(String locator, SelectorType selectorType, String text) {
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            element.clear();
            element.sendKeys(text);
            logger.info("Elemente metin gönderildi ve önceki içerik temizlendi: " + locator + " (" + selectorType + ")");
        } catch (Exception e) {
            logger.error("Elemente metin gönderilirken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    // Belirtilen elementin seçili olduğunu kontrol eder
    public static boolean isElementSelected(String locator, SelectorType selectorType) {
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            return element.isSelected();
        } catch (Exception e) {
            logger.error("Elementin seçili olduğu kontrol edilirken bir hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // Belirtilen elementin etkin olduğunu kontrol eder
    public static boolean isElementEnabled(String locator, SelectorType selectorType) {
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            return element.isEnabled();
        } catch (Exception e) {
            logger.error("Elementin etkin olduğu kontrol edilirken bir hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // Belirtilen elementin ekranın üst kısmına kaydırılmasını sağlar
    public static void scrollToTop() {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, 0)");
            logger.info("Sayfa başlangıcına kaydırıldı");
        } catch (Exception e) {
            logger.error("Sayfa başlangıcına kaydırılırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    // Belirtilen elementin ekranın alt kısmına kaydırılmasını sağlar
    public static void scrollToBottom() {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            logger.info("Sayfa sonuna kaydırıldı");
        } catch (Exception e) {
            logger.error("Sayfa sonuna kaydırılırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    // Belirtilen elementin ekranın belirli bir piksel aşağısına kaydırılmasını sağlar
    public static void scrollDownByPixels(int pixels) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0," + pixels + ")");
            logger.info("Sayfa " + pixels + " piksel aşağı kaydırıldı");
        } catch (Exception e) {
            logger.error("Sayfa " + pixels + " piksel aşağı kaydırılırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    public static void scrollDownByPercentage(int percentage) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            long innerHeight = (long) js.executeScript("return window.innerHeight");
            long scrollHeight = (long) js.executeScript("return document.body.scrollHeight");
            long scrollDistance = (scrollHeight - innerHeight) * percentage / 100;
            js.executeScript("window.scrollBy(0, arguments[0])", scrollDistance);
            logger.info("Sayfa %" + percentage + " aşağı kaydırıldı");
        } catch (Exception e) {
            logger.error("Sayfa %" + percentage + " aşağı kaydırılırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    /* OLD
    public static void clickRandomElement(String locator, SelectorType selectorType) {
        try {
            List<WebElement> elements = getDriver().findElements(getSelector(locator, selectorType));
            if (!elements.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(elements.size());
                elements.get(randomIndex).click();
                logger.info("Rastgele bir elemente tıklandı: " + locator + " (" + selectorType + ")");
            } else {
                logger.warn("Belirtilen locator ile eşleşen element bulunamadı: " + locator + " (" + selectorType + ")");
            }
        } catch (Exception e) {
            logger.error("Rastgele bir elemente tıklanırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }
     */

    public static void clickRandomElement(String locator, SelectorType selectorType) {
        try {
            WebElement randomElement = getRandomElement(locator, selectorType);
            if (randomElement != null) {
                randomElement.click();
                logger.info("Rastgele bir elemente tıklandı: " + locator + " (" + selectorType + ")");
            } else {
                logger.warn("Belirtilen locator ile eşleşen element bulunamadı: " + locator + " (" + selectorType + ")");
            }
        } catch (Exception e) {
            logger.error("Rastgele bir elemente tıklanırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    // Rastgele bir element seçme
    private static WebElement getRandomElement(String locator, SelectorType selectorType) {
        try {
            List<WebElement> elements = getDriver().findElements(getSelector(locator, selectorType));
            if (!elements.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(elements.size());
                return elements.get(randomIndex);
            } else {
                logger.warn("Belirtilen locator ile eşleşen element bulunamadı: " + locator + " (" + selectorType + ")");
            }
        } catch (Exception e) {
            logger.error("Rastgele bir element seçilirken bir hata oluştu: " + e.getMessage());
            throw e;
        }
        return null;
    }


    // Belirtilen elementin ekranın belirli bir piksel yukarısına kaydırılmasını sağlar
    public static void scrollUpByPixels(int pixels) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,-" + pixels + ")");
            logger.info("Sayfa " + pixels + " piksel yukarı kaydırıldı");
        } catch (Exception e) {
            logger.error("Sayfa " + pixels + " piksel yukarı kaydırılırken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }

    // Belirtilen elementin value özelliğini alır
    public static String getElementValue(String locator, SelectorType selectorType) {
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            String value = element.getAttribute("value");
            logger.info("Elementin value değeri alındı: " + value);
            return value;
        } catch (Exception e) {
            logger.error("Elementin value değeri alınırken bir hata oluştu: " + e.getMessage());
            return null;
        }
    }

    public static void sendKeysWithButton(String locator, CharSequence key, SelectorType selectorType) {
        try {
            WebElement element = getDriver().findElement(getSelector(locator, selectorType));
            element.sendKeys(key);
            logger.info("Elemente tuş gönderildi: " + key.toString() + locator + " (" + selectorType + ")");
        } catch (Exception e) {
            logger.error("Elemente tuş gönderilirken bir hata oluştu: " + e.getMessage());
            throw e;
        }
    }
}
