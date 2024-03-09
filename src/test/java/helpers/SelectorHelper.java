package helpers;

import settings.SelectorType;
import org.openqa.selenium.By;

public class SelectorHelper {

    public static By getSelector(String locator, SelectorType selectorType) {
        switch (selectorType) {
            case ID:
                return By.id(locator);
            case NAME:
                return By.name(locator);
            case CLASSNAME:
                return By.className(locator);
            case TAGNAME:
                return By.tagName(locator);
            case LINKTEXT:
                return By.linkText(locator);
            case PARTIALLINKTEXT:
                return By.partialLinkText(locator);
            case CSS:
                return By.cssSelector(locator);
            case XPATH:
                return By.xpath(locator);
            default:
                throw new IllegalArgumentException("Geçersiz seçici türü: " + selectorType);
        }
    }
}
