package tsatualdypov.apple;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppleMainPage {
    private WebDriver driver;

    @FindBy(xpath = "//button[@class='btn btn-sign-in']")
    private WebElement signInButton;

    @FindBy(xpath = "//button[@id='dropdownMenuButton']/span[1]")
    private WebElement userEmail;

    @FindBy(xpath = "//button[@id='dropdownMenuButton']")
    private WebElement userAccountButton;

    @FindBy(xpath = "//button[@class='btn btn-logout']")
    private WebElement userAccountLogoutButton;

    public AppleMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void signIn() {
        this.signInButton.click();
    }

    public String getUserEmail() {
        return this.userEmail.getText();
    }

    public void openAccountMenu() {
        new WebDriverWait(this.driver, 10)
                .until(ExpectedConditions.elementToBeClickable(this.userAccountButton))
                .click();
    }

    public void logout() {
        this.userAccountLogoutButton.click();
    }

    public Boolean isLoggedIn() {
        return !this.signInButton.isDisplayed();
    }
}
