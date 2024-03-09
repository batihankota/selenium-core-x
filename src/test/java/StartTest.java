// Allure Imports
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import static io.qameta.allure.SeverityLevel.MINOR;
// JUnit Imports
import org.junit.*;
// Class Imports
import static settings.DriverSettings.*;
import static settings.SelectorType.*;
import static definitions.ElementDefinitions.*;
import static definitions.WaitDefinitions.*;
import static definitions.FileDefinitions.*;
// Key Imports
import static org.openqa.selenium.Keys.*;



//Allure Kodları:
// allure serve target/allure-results
// mvn clean test
// mvn clean test -Dtest=StartTest#testSpecialCase



public class StartTest {
    @BeforeClass
    public static void setUp() {
        initWebDriver();
    }
    @AfterClass
    public static void tearDown() {
        quitWebDriver();
    }


    @Test
    @DisplayName("Cimri Buy an Item")
    @Description("")
    @Feature("headphone_buy")
    @Severity(MINOR)
    @Owner("Batıhan Kota")
    @Link(name = "Cimri", url = "https://www.cimri.com")
    //@Issue("")
    //@TmsLink("")
    public void cimriTest() {
        navigateToURL("https://www.cimri.com");
        clickToElement("div[class='SearchBox_searchBarContainer__XlvBP']", CSS);
        waitUntilElementIsDisplayed("search-input", 5, ID);
        writeToElementFromCSV("test.csv", 1, 1, "search-input", ID);
        sendKeysWithButton("search-input", ENTER, ID);
        waitUntilElementIsDisplayed("div[data-cs='FilterWrapper']", 5, CSS);
        clickToElement("button[title='0 TL - 100 TL']", CSS);
        scrollDownByPercentage(20);
        getElementValue("input[name='maxPriceText']", CSS);
        clickRandomElement("a[class='link-detail']", CSS);
        waitForPageLoad(5);
        getElementText("span[class='Offer_price__wzExN']", CSS);
        doesElementTextContain("a[class='JumpButton_button__CppBA']", "Mağazaya Git", CSS);
        clickToElement("a[class='JumpButton_button__CppBA']", CSS);
        waitForPageLoad(5);
    }


    @Test
    @DisplayName("Steam")
    @Description("")
    @Feature("game_buy")
    @Severity(MINOR)
    @Owner("Batıhan Kota")
    @Link(name = "Steam", url = "https://store.steampowered.com/?l=turkish")
    //@Issue("")
    //@TmsLink("")
    public void steamTest() {
        navigateToURL("https://store.steampowered.com/?l=turkish");
        waitForPageLoad(5);
        scrollDownByPercentage(30);
        clickToElement("img[alt='The House of Da Vinci']", CSS);
        waitUntilElementIsDisplayed("#game_area_purchase_section_add_to_cart_124062 > div.game_purchase_action > div > div.btn_addtocart", 5, CSS);
        clickToElement("#game_area_purchase_section_add_to_cart_124062 > div.game_purchase_action > div > div.btn_addtocart", CSS);
        waitUntilElementIsDisplayed("button[class='DialogButton _DialogLayout Primary Focusable']", 4,CSS);
        clickToElement("button[class='DialogButton _DialogLayout Primary Focusable']", CSS);
        waitUntilElementIsDisplayed("div[class='pVXX8Pzc4JbT40TP4RwRG']", 4,CSS);
        doesElementTextContain("div[class='pVXX8Pzc4JbT40TP4RwRG']", "The House of Da Vinci", CSS);
    }

}
