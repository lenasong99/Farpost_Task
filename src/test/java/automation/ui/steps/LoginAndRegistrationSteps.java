package automation.ui.steps;

import automation.ui.pages.HomePage;
import automation.ui.pages.LoginPage;
import automation.ui.utils.ConfigReader;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static automation.ui.utils.Driver.getDriver;

public class LoginAndRegistrationSteps {
    WebDriver driver = getDriver();
    HomePage homePage =new HomePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }
    @Given(": The user is on the home page")
    public void the_user_is_on_the_home_page() {
        getDriver().get(ConfigReader.getPropertiesValue("farpostHomePage"));
        Assertions.assertEquals(ConfigReader.getPropertiesValue("farpostHomePage"), getDriver().getCurrentUrl());
    }
    @Given(": The user clicks on login and registration link")
    public void the_user_clicks_on_login_and_registration_link() {
        homePage.loginOrRegistration();
    }

    @When(": The user inputs invalid login {string} ang password {string}")
    public void the_user_inputs_invalid_login_ang_password(String login, String password) {
        loginPage.loginWithPassword(login, password);
    }


    @When(": The user should see red messages {string} under the login and {string} under the password")
    public void theUserShouldSeeRedMessagesUnderTheLoginAndUnderThePassword(String error1, String error2) {
        Assertions.assertEquals(error1, loginPage.getLoginErrorMessages());
        Assertions.assertEquals(error2, loginPage.getPasswordErrorMessages());
    }

    @When(": The user enters valid login and password")
    public void the_user_enters_valid_login_and_password() {
        loginPage.cleanLoginAndPasswordTxtBoxes();
        loginPage.loginWithPassword(ConfigReader.getPropertiesValue("farpostLogin"), ConfigReader.getPropertiesValue("farpostPassword"));
    }
    @Then("The user sees profile page")
    public void the_user_sees_profile_page() {
        Assertions.assertEquals(getDriver().getCurrentUrl(), "https://www.farpost.ru/personal/");
        WebElement profileName = driver.findElement(By.xpath("//span[@class = \"userNick auto-shy\"]"));
        Assertions.assertEquals(profileName.getText(), ConfigReader.getPropertiesValue("farpostLogin"));

    }



}
