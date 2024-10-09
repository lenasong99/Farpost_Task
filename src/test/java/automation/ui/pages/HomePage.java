package automation.ui.pages;

import automation.ui.utils.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static automation.ui.utils.Driver.getDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id=\"menu\"]/div/table/tbody/tr/td[2]/table/tbody/tr/td[4]/noindex/a")
    private WebElement loginRegistrationLink;

    @FindBy(id = "search")
    private WebElement searchTxtBox;

    @FindBy(xpath = "//input[@value = \"Найти\"]")
    private WebElement searchBtn;

    @FindBy(xpath = "//*[@id=\"viewdirSearchForm\"]/div[2]/div[2]/div[1]/div[2]/a[7]")
    private WebElement priceFilterLink;

    @FindBy(id = "price_max")
    private WebElement priceMaxTxtBox;



    public void loginOrRegistration() {
        loginRegistrationLink.click();
        Assertions.assertEquals(ConfigReader.getPropertiesValue("farpostLoginPage"), getDriver().getCurrentUrl());
    }

    public void search(String item) {
        searchTxtBox.sendKeys(item);
        searchBtn.click();
    }

    public void priceMaxFilter(String sum) {
        priceFilterLink.click();
        priceMaxTxtBox.sendKeys(sum);
        searchBtn.click();
    }

}
