package settings;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSettings {

    private static WebDriver driver;
    private static final Logger logger = Logger.getLogger(DriverSettings.class);

    // Webdriver'in başlatılması
    public static void initWebDriver() {
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            logger.info("WebDriver başlatıldı.");
        } catch (Exception e) {
            logger.error("WebDriver başlatılırken bir hata oluştu: " + e.getMessage());
        }
    }

    // Belirtilen URL'ye git
    public static void navigateToURL(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            logger.error("Sayfa açılırken bir hata oluştu: " + e.getMessage());
        }
    }

    // WebDriver'ı kapat
    public static void quitWebDriver() {
        try {
            if (driver != null) {
                driver.quit();
                logger.info("WebDriver kapatıldı.");
            }
        } catch (Exception e) {
            logger.error("WebDriver kapatılırken bir hata oluştu: " + e.getMessage());
        }
    }

    // WebDriver nesnesini döndürme
    public static WebDriver getWebDriver() {
        return driver;
    }
}
