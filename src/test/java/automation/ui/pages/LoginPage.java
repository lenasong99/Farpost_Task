package automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "sign")
    private WebElement loginTxtBox;

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(id = "signbutton")
    private WebElement signWithPasswordBtn;

    @FindBy(xpath = "//*[@id=\"sign_errors\"]/div")
    private WebElement loginErrorMessage;

    @FindBy(xpath = "//*[@id=\"password_errors\"]/div")
    private WebElement passwordErrorMessage;

    public void loginWithPassword(String login, String password) {
        loginTxtBox.sendKeys(login);
        passwordTxtBox.sendKeys(password);
        signWithPasswordBtn.click();
    }

    public String getLoginErrorMessages() {
        return loginErrorMessage.getText();
    }
    public String getPasswordErrorMessages() {
        return passwordErrorMessage.getText();
    }

    public void cleanLoginAndPasswordTxtBoxes() {
        loginTxtBox.clear();
        passwordTxtBox.clear();
    }
}
