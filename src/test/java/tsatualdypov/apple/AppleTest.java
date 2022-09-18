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
        // On the main page we are opening
        // sign in modal form.
        mainPage.openSignIn();

        this.implicitlyWait(2);

        // After modal login page opened, we are
        // inputting email and pressing on submit
        // button.
        loginPage.inputEmail("howabiw236@edxplus.com");
        loginPage.submit();

        this.implicitlyWait(2);

        // After email was submitted password field
        // will be displayed, inputting password in
        // this field and submitting it.
        loginPage.inputPassword("howabiw236@edxplus.com");
        loginPage.submit();

        this.implicitlyWait(5);

        // Finally, checking if used email is matching
        // the actual email shown in the top right corner
        // on the website.
        Assert.assertEquals("howabiw236@edxplus.com", mainPage.getUserEmail());
    }

    @Test
    public void testLogout() {
        // Opening account menu on the main page
        // by clicking on the email label in the
        // top right corner.
        mainPage.openAccountMenu();

        this.implicitlyWait(2);

        // In the opened menu pressing logout button.
        mainPage.logout();

        this.implicitlyWait(5);

        // Finally, verifying that user is logged in
        // by checking whether login button is displayed.
        Assert.assertFalse(mainPage.isLoggedIn());
    }

    @Test
    public void testSearch() {
        String query = "iPhone 13";

        // Opening search by clicking on the
        // magnifying glass icon located in the
        // website's header.
        searchPage.openSearch();

        this.implicitlyWait(2);

        // Inputting query to the displayed
        // search field.
        searchPage.inputQuery(query);

        this.implicitlyWait(10);

        // Finally, checking if search query in
        // the result page contains the query
        // that we have entered in the search field.
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
