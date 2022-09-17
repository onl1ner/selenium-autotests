package tsatualdypov.apple;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AppleSearchPage {
    private WebDriver driver;

    @FindBy(xpath = "//button[contains(@class, 'btn-header-search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@formcontrolname='searchControl']")
    private WebElement searchField;

    @FindBy(xpath = "//p[@class='default-info']")
    private WebElement searchQuery;

    public AppleSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openSearch() {
        this.searchButton.click();
    }

    public void search(String query) {
        this.searchField.sendKeys(query);
        this.searchField.sendKeys(Keys.ENTER);
    }

    public String getSearchQuery() {
        return this.searchQuery.getText();
    }
}
