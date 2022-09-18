package tsatualdypov.aviasales;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AviasalesTest {
    private static WebDriver driver;

    private static AviasalesSearchPage searchPage;
    private static AviasalesTicketPage ticketPage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.aviasales.kz/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        searchPage = new AviasalesSearchPage(driver);
        ticketPage = new AviasalesTicketPage(driver);
    }

    @Test
    public void testBooking() {
        // On the search page inputting origin
        // city in the origin city field.
        searchPage.inputOrigin("Астана");

        this.implicitlyWait(5);

        // Inputting destination city in the
        // destination city field.
        searchPage.inputDestination("Алматы");

        this.implicitlyWait(5);

        // Selecting random date by picking
        // next month's any week and any day
        // in it.
        searchPage.selectRandomDate();

        this.implicitlyWait(5);

        // Pressing search button to perform
        // ticket search.
        searchPage.performSearch();

        this.implicitlyWait(60);

        // In the opened search result page
        // selecting first ticket.
        searchPage.selectFirstSearchResult();

        this.implicitlyWait(5);

        // Capturing number of tabs before
        // pressing the buy ticket button.
        ArrayList<String> tabsBefore = new ArrayList<String>(driver.getWindowHandles());

        // In the opened modal ticket page
        // pressing on the buy ticket button.
        ticketPage.buy();

        // Getting number of tabs after
        // clicking on the buy ticket button.
        ArrayList<String> tabsAfter = new ArrayList<String>(driver.getWindowHandles());

        // Finally, checking if number of tabs
        // before clicking on the buy ticket
        // is less than number of tabs after clicking
        // on this button, if it is true, this means
        // that website have successfully redirected
        // user to the buy ticket page.
        Assert.assertTrue(tabsBefore.size() < tabsAfter.size());
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    private void implicitlyWait(long duration) {
        driver.manage().timeouts().implicitlyWait(duration, TimeUnit.SECONDS);
    }
}
