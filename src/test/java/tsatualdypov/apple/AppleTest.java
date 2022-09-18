package tsatualdypov.apple;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AppleTest {
    private static WebDriver driver;

    private static AppleMainPage mainPage;
    private static AppleLoginPage loginPage;
    private static AppleSearchPage searchPage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://ispace.kz/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        mainPage = new AppleMainPage(driver);
        loginPage = new AppleLoginPage(driver);
        searchPage = new AppleSearchPage(driver);
    }

    @Test
    public void testLogin() {
        mainPage.openSignIn();

        this.implicitlyWait(2);

        loginPage.inputEmail("howabiw236@edxplus.com");
        loginPage.submit();

        this.implicitlyWait(2);

        loginPage.inputPassword("howabiw236@edxplus.com");
        loginPage.submit();

        this.implicitlyWait(5);

        Assert.assertEquals("howabiw236@edxplus.com", mainPage.getUserEmail());
    }

    @Test
    public void testLogout() {
        mainPage.openAccountMenu();

        this.implicitlyWait(2);

        mainPage.logout();

        this.implicitlyWait(5);

        Assert.assertFalse(mainPage.isLoggedIn());
    }

    @Test
    public void testSearch() {
        String query = "iPhone 13";

        searchPage.openSearch();

        this.implicitlyWait(2);

        searchPage.search(query);

        this.implicitlyWait(10);

        Assert.assertTrue(searchPage.getSearchQuery().contains(query));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    private void implicitlyWait(long duration) {
        driver.manage().timeouts().implicitlyWait(duration, TimeUnit.SECONDS);
    }
}
