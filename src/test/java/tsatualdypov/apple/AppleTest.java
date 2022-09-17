package tsatualdypov.apple;

import org.junit.Assert;
import org.junit.Test;

import tsatualdypov.BaseTest;

public class AppleTest extends BaseTest {
    @Test
    public void testLogin() {
        driver.get("https://ispace.kz/");

        implicitlyWait(5);

        AppleMainPage mainPage = new AppleMainPage(driver);
        AppleLoginPage loginPage = new AppleLoginPage(driver);

        mainPage.signIn();

        implicitlyWait(2);

        loginPage.inputEmail("howabiw236@edxplus.com");
        loginPage.submit();

        implicitlyWait(2);

        loginPage.inputPassword("howabiw236@edxplus.com");
        loginPage.submit();

        implicitlyWait(2);

        Assert.assertEquals("howabiw236@edxplus.com", mainPage.getUserEmail());
    }
}
