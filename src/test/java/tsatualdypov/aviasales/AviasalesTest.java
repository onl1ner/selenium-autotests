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
        searchPage.inputOrigin("Астана");

        this.implicitlyWait(5);

        searchPage.inputDestination("Алматы");

        this.implicitlyWait(5);

        searchPage.selectRandomDate();

        this.implicitlyWait(5);

        searchPage.performSearch();

        this.implicitlyWait(60);

        searchPage.selectFirstSearchResult();

        this.implicitlyWait(5);

        ticketPage.buy();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        Assert.assertEquals(2, tabs.size());
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    private void implicitlyWait(long duration) {
        driver.manage().timeouts().implicitlyWait(duration, TimeUnit.SECONDS);
    }
}
