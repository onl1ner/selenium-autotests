package tsatualdypov.apple;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppleMainPage {
    private WebDriver driver;

    @FindBy(xpath = "//button[@class='btn btn-sign-in']")
    private WebElement signInButton;

    @FindBy(xpath = "//button[@id='dropdownMenuButton']/span[1]")
    private WebElement userEmail;

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
}
