package tsatualdypov.aviasales;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AviasalesTicketPage {
    @FindBy(css = "a[class*='buy-button']")
    private WebElement buyButton;

    public AviasalesTicketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void buy() {
        this.buyButton.click();
    }
}
