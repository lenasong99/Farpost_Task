package automation.ui.steps;

import automation.ui.pages.HomePage;
import automation.ui.utils.ConfigReader;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automation.ui.utils.Driver.getDriver;

public class SearchSteps {
    WebDriver driver = getDriver();
    HomePage homePage = new HomePage(driver);


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }

    @Given("The user opens the main page")
    public void the_user_opens_the_main_page() {
        getDriver().get(ConfigReader.getPropertiesValue("farpostVladivostokPage"));
        Assertions.assertEquals(ConfigReader.getPropertiesValue("farpostVladivostokPage"), getDriver().getCurrentUrl());
    }
    @When("The user enters {string} into the search bar and click the Search button")
    public void the_user_enters_into_the_search_bar_and_click_the_button(String item) throws InterruptedException {
        homePage.search(item);
        Thread.sleep(2000);
        Assertions.assertEquals(getDriver().getCurrentUrl(), "https://www.farpost.ru/vladivostok/tech/communication/cellphones/+/Apple+iPhone/?_suggest=1&query=iphone");
    }

    @And("The user should see a block with three elements and first element of the block should be active")
    public void theUserShouldSeeABlockWithThreeElementsAndFirstElementOfTheBlockShouldBeActive() {
        List<WebElement> blockWithThreeElements = driver.findElements(By.xpath("//*[contains(@class, \"dir-search-suggest__item\")]"));
        Assertions.assertEquals(3, blockWithThreeElements.size());
        WebElement firstElement = blockWithThreeElements.get(0);
        Assertions.assertTrue(firstElement.getAttribute("class").contains("item_active"));
    }


    @When("The user selects the price filter and sets the maximum price to {string} rubles")
    public void theUserSelectsThePriceFilterAndSetsTheMaximumPriceToRubles(String maxPrice) throws InterruptedException {
        homePage.priceMaxFilter(maxPrice);
        Thread.sleep(2000);
        System.out.println(getDriver().getCurrentUrl());
    }

    @Then("The user should be on a page with the URL that starts with {string}")
    public void theUserShouldBeOnAPageWithTheURLThatStartsWith(String expectedStartUrl) {
        Assertions.assertTrue(getDriver().getCurrentUrl().startsWith(expectedStartUrl));
    }

    @Then("The block from the previous step should not be present")
    public void the_block_from_the_previous_step_should_not_be_present() {
        List<WebElement> blockWithThreeElements = driver.findElements(By.xpath("//*[contains(@class, \"dir-search-suggest__item\")]"));
        Assertions.assertEquals(0, blockWithThreeElements.size());
    }
    @Then("There should be no offers priced higher than {int} rubles on the page")
    public void there_should_be_no_offers_priced_higher_than_rubles_on_the_page(Integer sum) {
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class = \"price-block__price\"]"));
        for (WebElement price : prices) {
            int num = Integer.parseInt(price.getText().replaceAll("[^\\d]", ""));
            System.out.println(num);
            Assertions.assertTrue(num <= sum);
        }

    }

}
