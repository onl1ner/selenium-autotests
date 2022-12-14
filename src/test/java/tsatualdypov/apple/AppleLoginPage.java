package tsatualdypov.apple;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppleLoginPage {
    @FindBy(css = "input[id='login']")
    private WebElement emailField;

    @FindBy(css = "input[id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@class='btn btn-brand-blue circled ng-star-inserted']")
    private WebElement continueButton;

    public AppleLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void inputEmail(String email) {
        this.clearField(this.emailField);
        this.emailField.sendKeys(email);
    }

    public void inputPassword(String password) {
        this.clearField(this.passwordField);
        this.passwordField.sendKeys(password);
    }

    public void submit() {
        this.continueButton.click();
    }

    private void clearField(WebElement field) {
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(Keys.DELETE);
    }
}
