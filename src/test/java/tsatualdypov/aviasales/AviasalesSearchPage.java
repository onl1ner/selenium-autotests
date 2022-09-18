package tsatualdypov.aviasales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AviasalesSearchPage {
    private final WebDriver driver;

    @FindBy(css = "input[id='origin']")
    private WebElement originField;

    @FindBy(css = "input[id='destination']")
    private WebElement destinationField;

    @FindBy(css = "div[class*='booking']")
    private WebElement hotelCheckbox;

    @FindBy(css = "button[class*='submit']")
    private WebElement submitButton;

    public AviasalesSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputOrigin(String origin) {
        this.originField.sendKeys(origin);
    }

    public void inputDestination(String destination) {
        this.destinationField.sendKeys(destination);
    }

    public void selectRandomDate() {
        new WebDriverWait(this.driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'field --departure')]")))
                .click();

        String calendarMonthsXPath = "div[@class='calendar__months']";
        String calendarNextMonthXPath = "div[2]";
        String calendarMonthBodyXPath = "div[@class='calendar__weeks-body']";

        int monthRow = 2 + (int) (Math.random() * 2);
        int monthColumn = 1 + (int) (Math.random() * 6);

        String xpath = new StringBuilder()
                .append("//")
                .append(calendarMonthsXPath)
                .append("/")
                .append(calendarNextMonthXPath)
                .append("/")
                .append(calendarMonthBodyXPath)
                .append("/")
                .append(String.format("div[%d]/div[%d]", monthRow, monthColumn))
                .toString();

        new WebDriverWait(this.driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
                .click();
    }

    public void performSearch() {
        this.hotelCheckbox.click();
        this.submitButton.click();
    }

    public void selectFirstSearchResult() {
        this.driver.findElements(By.xpath("//div[@class='product-list__item fade-enter-done']"))
                .get(0)
                .click();
    }
}
